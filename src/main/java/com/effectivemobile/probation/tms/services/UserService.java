package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.user.UserDto;

public interface UserService {
    UserDto get(long id);

    void delete(long id);
}
