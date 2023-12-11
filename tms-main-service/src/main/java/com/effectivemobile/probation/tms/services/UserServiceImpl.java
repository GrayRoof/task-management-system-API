package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserDto;
import com.effectivemobile.probation.tms.model.user.UserMapper;
import com.effectivemobile.probation.tms.paginations.OffsetPageable;
import com.effectivemobile.probation.tms.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User getEntity(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public UserDto get(long id) {
        User user = userRepository.getReferenceById(id);
        return UserMapper.toDto(user);
    }

    @Override
    public Collection<UserDto> getAll(Integer from, Integer size) {
        Page<User> result;
        result = userRepository.findAll(OffsetPageable.of(from, size, Sort.unsorted()));
        return result.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    /*
    @Override
    public UserDto add(UserDto userDto) {
        User newUser = userRepository.save(UserMapper.toUser(userDto));
        log.info("Пользователь создан с идентификатором #{}", newUser.getId());
        return UserMapper.toDto(newUser);
    }

     */
}
