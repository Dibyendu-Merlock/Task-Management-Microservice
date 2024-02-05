package com.dibyendu.taskService.service.Impl;

import com.dibyendu.taskService.entity.Task;
import com.dibyendu.taskService.entity.TaskStatus;
import com.dibyendu.taskService.repository.TaskRepository;
import com.dibyendu.taskService.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService
{
    @Autowired
    private TaskRepository taskRepository;


    /**
     * @param task
     * @param requesterRole
     * @return
     * @throws Exception
     */
    @Override
    public Task createTask(Task task, String requesterRole) throws Exception
    {
        if(!requesterRole.equals(("ROLE_ADMIN")))
        {
            throw new Exception("Only Admins can create Task");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Task getTaskById(Long id) throws Exception
    {
        return taskRepository.findById(id).orElseThrow(()->new Exception("Task Not Found with id---> " + id));
    }

    /**
     * @param taskStatus
     * @return
     */
    @Override
    public List<Task> getAllTask(TaskStatus taskStatus)
    {
        List<Task> allTask = taskRepository.findAll();
        List<Task> filteredTask = allTask.stream().filter(
                task->taskStatus==null || task.getStatus().name().equalsIgnoreCase(taskStatus.toString())
        ).collect(Collectors.toList());

        return filteredTask;
    }

    /**
     * @param id
     * @param updatedTask
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception
    {
        Task existingTask = getTaskById(id);
        if(updatedTask.getTitle()!=null)
        {
            existingTask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getImage()!=null)
        {
            existingTask.setImage(updatedTask.getImage());
        }
        if(updatedTask.getDescription()!=null)
        {
            existingTask.setDescription(updatedTask.getDescription());
        }
        if(updatedTask.getStatus()!=null)
        {
            existingTask.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getDeadLine()!=null)
        {
            existingTask.setDeadLine(updatedTask.getDeadLine());
        }

        return taskRepository.save(existingTask);
    }

    /**
     * @param id
     */
    @Override
    public void deleteTask(Long id) throws Exception
    {
        getTaskById(id);
        taskRepository.deleteById(id);

    }

    /**
     * @param userId
     * @param taskId
     * @return
     * @throws Exception
     */
    @Override
    public Task assignToUser(Long userId, Long taskId) throws Exception
    {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.ASSIGNED);

        return taskRepository.save(task);
    }

    /**
     * @param userId
     * @param taskStatus
     * @return
     */
    @Override
    public List<Task> assignedUsersTask(Long userId, TaskStatus taskStatus)
    {
        List<Task> allTasks = taskRepository.findByAssignedUserId(userId);
        List<Task> filteredTask = allTasks.stream().filter(
                task->taskStatus==null || task.getStatus().name().equalsIgnoreCase(taskStatus.toString())
        ).collect(Collectors.toList());

        return filteredTask;
    }

    /**
     * @param taskId
     * @return
     * @throws Exception
     */
    @Override
    public Task completeTask(Long taskId) throws Exception
    {
        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }
}
