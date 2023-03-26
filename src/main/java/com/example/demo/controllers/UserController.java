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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        Optional<User> result = userService.findById(id);
        if(!result.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @PostMapping
    public Object insert(@RequestBody @Valid UserDto user){
        if(userService.exitsEmailUser(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O e-mail já existe!");
        }
        User userModel = new User();
        BeanUtils.copyProperties(user, userModel);
        User result = userService.insert(userModel);
        return result;
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id){
        Optional<User> result = userService.findById(id);
        if(!result.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        userService.delete(result.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado!");
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserDto user){
        Optional<User> resultUser = userService.findById(id);
        if(!resultUser.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        User userNew = new User();
        BeanUtils.copyProperties(user, userNew);
        userNew.setId(resultUser.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userNew));
    }
}
