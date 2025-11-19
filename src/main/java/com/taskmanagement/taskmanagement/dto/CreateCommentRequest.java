package com.taskmanagement.taskmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    @NotBlank
    private String text;
    @NotNull
    private Long userId;
}
