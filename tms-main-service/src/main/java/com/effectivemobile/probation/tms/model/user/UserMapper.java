package com.effectivemobile.probation.tms.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    public static NestedUserDto toNestedDto(User user) {
        NestedUserDto userDto = new NestedUserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName() == null ? "" : userDto.getName(),
                userDto.getEmail(),
                ""
        );
    }
}
