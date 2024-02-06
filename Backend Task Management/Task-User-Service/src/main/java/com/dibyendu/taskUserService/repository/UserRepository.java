package com.dibyendu.taskUserService.repository;

import com.dibyendu.taskUserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByEmail(String email);

}
