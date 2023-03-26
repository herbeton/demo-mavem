package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    public Optional<User> findById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Transactional
    public User insert(@RequestBody User user){
        User userInser = userRepository.save(user);
        return userInser;
    }

    public boolean exitsEmailUser(String email) {
        return userRepository.existsByEmail(email);
    }
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
}
