package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserDto;

import java.util.Collection;

public interface UserService {

    /**
     * Возвращает Пользователя по идентификатору
     * @param id идентификатор Пользователя
     * @return User
     */
    User getEntity(long id);

    /**
     * Возвращает DTO Пользователя по идентификатору
     * @param id идентификатор Пользователя
     * @return UserDto
     */
    UserDto get(long id);

    /**
     * Возвращает список всех Пользователей
     * @param from стартовая позиция пагинации
     * @param size количество выводимых объектов на одной странице
     * @return Collection <'UserDto>
     */
    Collection<UserDto> getAll(Integer from, Integer size);

    /*
    UserDto add(UserDto userDto);


     */
}
