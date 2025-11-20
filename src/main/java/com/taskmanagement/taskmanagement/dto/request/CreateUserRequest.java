package com.taskmanagement.taskmanagement.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    @NotBlank
    private String name;
    @NotBlank @Email
    private String email;
}
