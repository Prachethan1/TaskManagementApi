package com.taskmanagement.taskmanagement.controller;

import com.taskmanagement.taskmanagement.dto.request.CreateTaskRequest;
import com.taskmanagement.taskmanagement.dto.request.UpdateTaskRequest;
import com.taskmanagement.taskmanagement.dto.response.TaskBaseResponse;
import com.taskmanagement.taskmanagement.dto.response.TaskResponse;
import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

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
    public ResponseEntity<List<TaskResponse>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest req){
        return ResponseEntity.ok(taskService.update(id, req.getTitle(), req.getDescription(), req.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/tags/{tagId}")
    public ResponseEntity<TaskBaseResponse> assignTag(@PathVariable Long taskId, @PathVariable Long tagId){
        return ResponseEntity.ok(taskService.assignTag(taskId, tagId));
    }

    @DeleteMapping("/{taskId}/tags/{tagId}")
    public ResponseEntity<TaskBaseResponse> removeTag(@PathVariable Long taskId, @PathVariable Long tagId){
        return ResponseEntity.ok(taskService.removeTag(taskId, tagId));
    }
}

