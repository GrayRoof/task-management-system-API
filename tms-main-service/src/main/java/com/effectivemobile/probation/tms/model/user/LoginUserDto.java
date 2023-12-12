package com.effectivemobile.probation.tms.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotEmpty(message = "Поле email не может быть пустым.")
    @Email(message = "Введенное значение не является адресом электронной почты.")
    private String email;
    @NotEmpty(message = "Поле password не может быть пустым.")
    private String password;
}
