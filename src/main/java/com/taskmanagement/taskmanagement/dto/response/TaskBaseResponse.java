package com.taskmanagement.taskmanagement.dto.response;

import com.taskmanagement.taskmanagement.entity.Tag;
import com.taskmanagement.taskmanagement.entity.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TaskBaseResponse {
    private Long id;
    private String title;
    private Set<TagResponse> tags;

    public TaskBaseResponse(Long id, String title, Set<TagResponse> tags) {
        this.id=id;
        this.title=title;
        this.tags = tags;
    }
}
