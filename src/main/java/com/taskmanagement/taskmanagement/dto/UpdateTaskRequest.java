package com.taskmanagement.taskmanagement.dto;

import com.taskmanagement.taskmanagement.entity.Status;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskRequest {

    @NotBlank
    private String title;
    private String description;
    private Status status;

}
