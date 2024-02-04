package com.dibyendu.taskUserService.service.Impl;

import com.dibyendu.taskUserService.config.JwtProvider;
import com.dibyendu.taskUserService.entity.User;
import com.dibyendu.taskUserService.repository.UserRepository;
import com.dibyendu.taskUserService.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements Userservice
{
    @Autowired
    private UserRepository userRepository;

    /**
     * @param jwt
     * @return
     */
    @Override
    public User getUserProfile(String jwt)
    {
        String email = JwtProvider.getEmailFromJwt(jwt);
        return userRepository.findByEmail(email);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
