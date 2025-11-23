package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.dto.response.CommentBaseResponse;
import com.taskmanagement.taskmanagement.dto.response.CommentResponse;
import com.taskmanagement.taskmanagement.dto.response.UserResponse;
import com.taskmanagement.taskmanagement.entity.Comment;
import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.entity.User;
import com.taskmanagement.taskmanagement.exception.ResourceNotFoundException;
import com.taskmanagement.taskmanagement.repository.CommentRepository;
import com.taskmanagement.taskmanagement.repository.TaskRepository;
import com.taskmanagement.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(TaskRepository taskRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public CommentResponse addComment(Long taskId, Long userId, String text) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Comment comment = new Comment(text);
        comment.setTask(task);
        comment.setUser(u);
        task.getComments().add(comment);
        commentRepository.save(comment);

        UserResponse user = new UserResponse(
                comment.getUser().getId(),
                comment.getUser().getName(),
                comment.getUser().getEmail()
        );
        return new CommentResponse(comment.getId(),
                comment.getText(),
                user);
    }

    public List<CommentBaseResponse> getAll(Long taskId) {
        if(!taskRepository.existsById(taskId)){
            throw new ResourceNotFoundException("Task not found");
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return task.getComments()
                .stream()
                .map(
                        comment -> new CommentBaseResponse(
                                comment.getId(),
                                comment.getText()
                        )
                ).toList();
    }
}
