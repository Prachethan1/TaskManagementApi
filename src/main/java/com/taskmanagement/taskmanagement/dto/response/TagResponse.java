package com.taskmanagement.taskmanagement.dto.response;

import com.taskmanagement.taskmanagement.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TagResponse {

    private Long id;
    private String name;

    public TagResponse(Long id, String name) {
        this.id=id;
        this.name=name;
    }
}
