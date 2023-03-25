package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findById(@PathVariable Long id){
        User user = userRepository.findById(id).get();
        return user;
    }

    public User insert(@RequestBody User user){
        User userInser = userRepository.save(user);
        return userInser;
    }
}
