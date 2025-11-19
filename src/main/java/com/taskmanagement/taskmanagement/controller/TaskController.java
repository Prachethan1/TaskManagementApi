package com.taskmanagement.taskmanagement.controller;

import com.taskmanagement.taskmanagement.dto.CreateTaskRequest;
import com.taskmanagement.taskmanagement.dto.TaskResponse;
import com.taskmanagement.taskmanagement.dto.UpdateTaskRequest;
import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request){
        Task task = new Task(request.getTitle(), request.getDescription(), request.getStatus());
        TaskResponse saved = taskService.createTask(task, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest req){
        return taskService.update(id, req.getTitle(), req.getDescription(), req.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
