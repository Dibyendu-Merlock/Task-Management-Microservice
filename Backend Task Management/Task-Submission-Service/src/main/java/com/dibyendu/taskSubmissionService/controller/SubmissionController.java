package com.dibyendu.taskSubmissionService.controller;

import com.dibyendu.taskSubmissionService.Dto.UserDto;
import com.dibyendu.taskSubmissionService.entity.Submission;
import com.dibyendu.taskSubmissionService.service.SubmissionService;
import com.dibyendu.taskSubmissionService.service.TaskService;
import com.dibyendu.taskSubmissionService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController
{
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Submission> submitTask(
            @RequestParam Long task_id,
            @RequestParam String gitHubLink,
            @RequestHeader ("Authorization") String jwt
    ) throws Exception
    {
        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(task_id,gitHubLink, userDto.getId(),jwt);

        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getsubmissionById(
            @PathVariable Long id,
            @RequestHeader ("Authorization") String jwt) throws Exception
    {
        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);

        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Submission>> getAllTaskSubmission(@RequestHeader ("Authorization") String jwt) throws Exception
    {
        UserDto userDto = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getAllTaskSubmission();

        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getTaskSubmissionsByTaskId(
            @PathVariable Long taskId,
            @RequestHeader ("Authorization") String jwt) throws Exception
    {
        UserDto userDto = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getTaskSubmissionsByTaskId(taskId);

        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptDeclineSubmission(
            @PathVariable Long id,
            @RequestParam("status") String status,
            @RequestHeader ("Authorization") String jwt) throws Exception
    {
        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(id,status);

        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

}
