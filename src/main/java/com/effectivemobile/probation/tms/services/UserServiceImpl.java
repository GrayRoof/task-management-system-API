package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserDto;
import com.effectivemobile.probation.tms.model.user.UserMapper;
import com.effectivemobile.probation.tms.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto get(long id) {
        User user = userRepository.getReferenceById(id);
        return UserMapper.toUserDto(user);
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public Collection<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto add(UserDto userDto) {
        User newUser = userRepository.save(UserMapper.toUser(userDto));
        log.info("Пользователь создан с идентификатором #{}", newUser.getId());
        return UserMapper.toUserDto(newUser);
    }

    @Override
    public UserDto patch(UserDto userDto, Long userId) {
        return null;
    }
}
