package com.example.demo.controllers;

import com.example.demo.entities.Department;
import com.example.demo.entities.User;
import com.example.demo.repositories.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public ResponseEntity<List<Department>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentRepository.findAll());
    }
}
