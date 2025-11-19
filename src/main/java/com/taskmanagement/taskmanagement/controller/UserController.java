package com.taskmanagement.taskmanagement.controller;

import com.taskmanagement.taskmanagement.dto.CreateUserRequest;
import com.taskmanagement.taskmanagement.entity.User;
import com.taskmanagement.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request){
        User user = new User(request.getName(), request.getEmail());
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){ return ResponseEntity.ok(userService.getAll()); }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<?> getTasksForUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getById(userId).getTasks());
    }
}
