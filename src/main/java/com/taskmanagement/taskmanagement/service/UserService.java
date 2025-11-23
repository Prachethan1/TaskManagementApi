package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.dto.response.TagResponse;
import com.taskmanagement.taskmanagement.dto.response.TaskResponse;
import com.taskmanagement.taskmanagement.dto.response.UserResponse;
import com.taskmanagement.taskmanagement.entity.User;
import com.taskmanagement.taskmanagement.exception.ResourceNotFoundException;
import com.taskmanagement.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponse(u.getId(), u.getName(), u.getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return user.getTasks().stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        new UserResponse(user.getId(), user.getName(), user.getEmail()),
                        task.getTags().stream()
                                .map(tag -> new TagResponse(tag.getId(), tag.getName()))
                                .collect(Collectors.toSet()),
                        task.getCreatedAt(),
                        task.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
}
