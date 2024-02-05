package com.dibyendu.taskService.controller;

import com.dibyendu.taskService.dto.UserDto;
import com.dibyendu.taskService.entity.Task;
import com.dibyendu.taskService.entity.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController
{
    @GetMapping("/tasks")
    public ResponseEntity<String> getAssignedUsersTask()
    {
        return new ResponseEntity<>("Welcome to Task Service", HttpStatus.OK);
    }

}
