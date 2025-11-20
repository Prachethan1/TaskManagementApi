package com.taskmanagement.taskmanagement.controller;

import com.taskmanagement.taskmanagement.dto.response.CommentResponse;
import com.taskmanagement.taskmanagement.dto.request.CreateCommentRequest;
import com.taskmanagement.taskmanagement.entity.Comment;
import com.taskmanagement.taskmanagement.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long taskId, @Valid @RequestBody CreateCommentRequest request) {
        CommentResponse saved = commentService.addComment(taskId, request.getUserId(), request.getText());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAll(@PathVariable Long taskId){
        return ResponseEntity.ok(commentService.getAll(taskId));
    }
}
