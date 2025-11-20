package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.entity.Tag;
import com.taskmanagement.taskmanagement.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(Tag tag) {
        Optional<Tag> existing = Optional.ofNullable(tagRepository.findByName(tag.getName()));
        if(existing.isPresent()){
            return existing.get();
        }
        return tagRepository.save(tag);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
