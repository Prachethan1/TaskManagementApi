package com.taskmanagement.taskmanagement.controller;

import com.taskmanagement.taskmanagement.dto.request.CreateTagRequest;
import com.taskmanagement.taskmanagement.entity.Tag;
import com.taskmanagement.taskmanagement.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;
    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@Valid @RequestBody CreateTagRequest request){
        Tag tag = new Tag(request.getName());
        Tag saved = tagService.createTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAll(){
        List<Tag> tag = tagService.getAll();
        return ResponseEntity.ok(tag);
    }
}
