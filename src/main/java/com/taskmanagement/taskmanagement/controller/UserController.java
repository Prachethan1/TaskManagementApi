package com.taskmanagement.taskmanagement.controller;

import com.taskmanagement.taskmanagement.dto.request.CreateUserRequest;
import com.taskmanagement.taskmanagement.dto.response.TaskResponse;
import com.taskmanagement.taskmanagement.dto.response.UserResponse;
import com.taskmanagement.taskmanagement.entity.User;
import com.taskmanagement.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request){
        User user = new User(request.getName(), request.getEmail());
        User saved = userService.createUser(user);
        UserResponse response = new UserResponse(saved.getId(), saved.getName(), saved.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksForUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getTasksForUser(userId));
    }
}
