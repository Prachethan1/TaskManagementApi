package com.taskmanagement.taskmanagement.dto.response;

import com.taskmanagement.taskmanagement.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse extends TaskBaseResponse{
    private String description;
    private Status status;
    private UserResponse user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public TaskResponse(Long id, String title, String description, Status status, UserResponse user, Set<TagResponse> tagResponse, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, title, tagResponse);
        this.description=description;
        this.status=status;
        this.user=user;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }
}
