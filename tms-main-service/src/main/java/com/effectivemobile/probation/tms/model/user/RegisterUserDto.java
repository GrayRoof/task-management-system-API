package com.effectivemobile.probation.tms.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotEmpty
    private String name;
    @NotEmpty
    @Email(message = "not email!")
    private String email;
    @NotEmpty
    private String password;
}
