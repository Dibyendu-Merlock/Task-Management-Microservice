package com.dibyendu.taskUserService.service;

import com.dibyendu.taskUserService.entity.User;

import java.util.List;

public interface Userservice
{
    public User getUserProfile(String jwt);
    public List<User> getAllUsers();
}
