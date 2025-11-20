package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.dto.response.TagResponse;
import com.taskmanagement.taskmanagement.dto.response.TaskBaseResponse;
import com.taskmanagement.taskmanagement.dto.response.TaskResponse;
import com.taskmanagement.taskmanagement.dto.response.UserResponse;
import com.taskmanagement.taskmanagement.entity.Status;
import com.taskmanagement.taskmanagement.entity.Tag;
import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.entity.User;
import com.taskmanagement.taskmanagement.exception.ResourceNotFoundException;
import com.taskmanagement.taskmanagement.repository.TagRepository;
import com.taskmanagement.taskmanagement.repository.TaskRepository;
import com.taskmanagement.taskmanagement.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public TaskResponse createTask(Task task, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task saved = taskRepository.save(task);
        return convertToResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> getAll(Optional<Status> status, Optional<String> tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Task> tasks;

        if (status.isPresent()) {
            tasks = taskRepository.findAllByStatus(status.get(), pageable);
        } else if (tag.isPresent()) {
            tasks = taskRepository.findAllByTagName(tag.get(), pageable);
        } else {
            tasks = taskRepository.findAll(pageable);
        }

        return tasks.map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public TaskResponse getById(Long id) {
        Task task = getTaskEntityById(id);
        return convertToResponse(task);
    }

    private Task getTaskEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Transactional
    public TaskResponse update(Long id, String title, String description, Status status) {
        Task task = getTaskEntityById(id);

        task.setTitle(title);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());

        if (status != null) task.setStatus(status);

        Task saved = taskRepository.save(task);
        return convertToResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        Task task = getTaskEntityById(id);
        taskRepository.delete(task);
    }

    @Transactional
    public TaskBaseResponse assignTag(Long taskId, Long tagId) {
        Task task = getTaskEntityById(taskId);
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));

        task.getTags().add(tag);
        tag.getTasks().add(task); // maintain bidirectional

        Task saved = taskRepository.save(task);

        Set<TagResponse> tagResponses = saved.getTags()
                .stream()
                .map(t -> new TagResponse(t.getId(), t.getName()))
                .collect(Collectors.toSet());

        return new TaskBaseResponse(saved.getId(), saved.getTitle(), tagResponses);
    }

    @Transactional
    public TaskBaseResponse removeTag(Long taskId, Long tagId) {
        Task task = getTaskEntityById(taskId);
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));

        task.getTags().remove(tag);
        tag.getTasks().remove(task); // maintain bidirectional

        Task saved = taskRepository.save(task);

        Set<TagResponse> tagResponses = saved.getTags()
                .stream()
                .map(t -> new TagResponse(t.getId(), t.getName()))
                .collect(Collectors.toSet());

        return new TaskBaseResponse(saved.getId(), saved.getTitle(), tagResponses);
    }

    private TaskResponse convertToResponse(Task task) {
        Set<TagResponse> tagResponses = task.getTags()
                .stream()
                .map(t -> new TagResponse(t.getId(), t.getName()))
                .collect(Collectors.toSet());

        UserResponse userResponse = new UserResponse(
                task.getUser().getId(),
                task.getUser().getName(),
                task.getUser().getEmail()
        );

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                userResponse,
                tagResponses,
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
