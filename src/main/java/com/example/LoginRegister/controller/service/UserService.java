package com.example.LoginRegister.controller.service;

import com.example.LoginRegister.controller.repository.UserRepository;
import com.example.LoginRegister.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @Repository
 * @Service  -> @Repository
 * @Controller -> @Service
 *
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
