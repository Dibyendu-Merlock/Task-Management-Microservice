package com.dibyendu.taskSubmissionService.service.Impl;

import com.dibyendu.taskSubmissionService.Dto.TaskDto;
import com.dibyendu.taskSubmissionService.entity.Submission;
import com.dibyendu.taskSubmissionService.repository.SubmissionRepository;
import com.dibyendu.taskSubmissionService.service.SubmissionService;
import com.dibyendu.taskSubmissionService.service.TaskService;
import com.dibyendu.taskSubmissionService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService
{
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    /**
     * @param taskId
     * @param gitHubLink
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Submission submitTask(Long taskId, String gitHubLink, Long userId, String jwt) throws Exception
    {
        TaskDto taskDto = taskService.getTaskById(taskId, jwt);
        if(taskDto!=null)
        {
            Submission submission = new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGitHubLink(gitHubLink);
            submission.setSubmissionTime(LocalDateTime.now());

            return submissionRepository.save(submission);
        }

        throw new Exception("Task not found with id : "+taskId);
    }

    /**
     * @param submissionId
     * @return
     * @throws Exception
     */
    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return submissionRepository.findById(submissionId).orElseThrow(
                ()-> new Exception("Task submission not found with Id: "+submissionId)
        );
    }

    /**
     * @return
     */
    @Override
    public List<Submission> getAllTaskSubmission()
    {
        return submissionRepository.findAll();
    }

    /**
     * @param taskId
     * @return
     */
    @Override
    public List<Submission> getTaskSubmissionsByTaskId(Long taskId)
    {
        return submissionRepository.findByTaskId(taskId);
    }

    /**
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception
    {
        Submission submission = getTaskSubmissionById(id);
        submission.setStatus(status);
        if(status.equals("ACCEPT"))
        {
            taskService.completeTask(submission.getTaskId());
        }

        return submissionRepository.save(submission);
    }
}
