package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> findAll(){
        List<User> result = userService.findAll();
        return result;
    }
    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id){
        User result = userService.findById(id);
        return result;
    }
    @PostMapping
    public User insert(@RequestBody User user){
        User result = userService.insert(user);
        return result;
    }
}
