package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @CrossOrigin
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<User> findAll(){
        List<User> result = repository.findAll();
        return result;
    }
}
