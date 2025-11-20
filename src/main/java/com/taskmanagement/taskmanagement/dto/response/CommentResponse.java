package com.taskmanagement.taskmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;
    private String text;
    private UserResponse user;
    private LocalDateTime createdAt;
}
