package com.dibyendu.taskService.entity;

public enum TaskStatus
{
    PENDING("PENDING"),
    ASSIGNED("ASSIGNED"),
    DONE("DONE");

    TaskStatus(String done) {
    }
}
