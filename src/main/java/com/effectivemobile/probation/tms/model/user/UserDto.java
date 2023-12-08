package com.effectivemobile.probation.tms.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String email;
    private String login;
    private String name;
    private String middleName;
    private String lastName;
}
