package com.dibyendu.taskSubmissionService.service;

import com.dibyendu.taskSubmissionService.entity.Submission;

import java.util.List;

public interface SubmissionService
{
    Submission submitTask(Long taskId, String gitHubLink, Long userId, String jwt) throws Exception;

    Submission getTaskSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllTaskSubmission();

    List<Submission> getTaskSubmissionsByTaskId(Long taskId);

    Submission acceptDeclineSubmission(Long id, String status) throws Exception;
}
