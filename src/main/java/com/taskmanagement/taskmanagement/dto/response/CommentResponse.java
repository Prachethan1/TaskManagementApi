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
public class CommentResponse extends CommentBaseResponse {
    private UserResponse user;

    public CommentResponse(Long id, String text, UserResponse user) {
        super(id, text);
        this.user = user;
    }
}
