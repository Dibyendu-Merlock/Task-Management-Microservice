package com.dibyendu.taskService.service;

import com.dibyendu.taskService.entity.Task;
import com.dibyendu.taskService.entity.TaskStatus;

import java.util.List;

public interface TaskService
{
    Task createTask(Task task, String requesterRole) throws Exception;

    Task getTaskById(Long id) throws Exception;
    List<Task> getAllTask(TaskStatus taskStatus);
    Task updateTask(Long id, Task updatedTask, Long userId) throws Exception;
    void deleteTask(Long id) throws Exception;
    Task assignToUser(Long userId, Long id) throws Exception;
    List<Task> assignedUsersTask(Long userId, TaskStatus taskStatus);

    Task completeTask(Long taskId) throws Exception;
}
