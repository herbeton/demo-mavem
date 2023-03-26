package com.example.demo.controllers;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public Object insert(@RequestBody @Validated UserDto user){
        if(userService.exitsEmailUser(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O e-mail já existe!");
        }
        User userModel = new User();
        BeanUtils.copyProperties(user, userModel);
        User result = userService.insert(userModel);
        return result;
    }
}
