package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserDto;

import java.util.Collection;

public interface UserService {

    User getEntity(long id);

    UserDto get(long id);

    void delete(long id);

    Collection<UserDto> getAll();

    UserDto add(UserDto userDto);

    UserDto patch(UserDto userDto, Long userId);
}
