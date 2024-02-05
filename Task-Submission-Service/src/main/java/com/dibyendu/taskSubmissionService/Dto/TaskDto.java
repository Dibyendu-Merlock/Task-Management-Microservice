package com.dibyendu.taskSubmissionService.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDto
{
    private Long id;
    private String title;
    private String description;
    private String image;
    private Long assignedUserId;
    // tags are nothing but the tech stacks we are going to use in the task or project
    private List<String> tags = new ArrayList<>();
    private TaskStatus status;
    private LocalDateTime deadLine;
    private LocalDateTime createdAt;
}
