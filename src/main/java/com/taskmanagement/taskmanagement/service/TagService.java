package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.dto.response.TagResponse;
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

    public TagResponse createTag(Tag tag) {
        Optional<Tag> existing = Optional.ofNullable(tagRepository.findByName(tag.getName()));
        if(existing.isPresent()){
            return new TagResponse(
                    tag.getId(),
                    tag.getName()
            );
        }
        tagRepository.save(tag);
        return new TagResponse(
                tag.getId(),
                tag.getName()
        );
    }

    public List<TagResponse> getAll() {
        return tagRepository.findAll()
                .stream()
                .map(tag -> new TagResponse(
                        tag.getId(),
                        tag.getName()
                )).toList();
    }
}
