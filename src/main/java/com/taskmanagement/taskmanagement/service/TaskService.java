package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.dto.TaskResponse;
import com.taskmanagement.taskmanagement.dto.UserResponse;
import com.taskmanagement.taskmanagement.entity.Status;
import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.entity.User;
import com.taskmanagement.taskmanagement.exception.ResourceNotFoundException;
import com.taskmanagement.taskmanagement.repository.TaskRepository;
import com.taskmanagement.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponse createTask(Task task, Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id"));
        task.setUser(u);
        taskRepository.save(task);
        UserResponse user = new UserResponse(
                task.getUser().getId(),
                task.getUser().getName(),
                task.getUser().getEmail()
        );
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                user,
                null,
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id"));
    }

    public Task update(Long id, String title, String description, Status status) {
        Task task = getById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        if(status != null) task.setStatus(status);
        return taskRepository.save(task);
    }

    public void delete(Long id){
        Task t = getById(id);
        taskRepository.delete(t);
    }
}
