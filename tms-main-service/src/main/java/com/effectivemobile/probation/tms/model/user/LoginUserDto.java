package com.effectivemobile.probation.tms.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
}