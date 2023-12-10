package com.effectivemobile.probation.tms.controllers;

import com.effectivemobile.probation.tms.exceptions.NotFoundException;
import com.effectivemobile.probation.tms.model.user.UserDto;
import com.effectivemobile.probation.tms.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable long id) throws NotFoundException {
        log.info("SERVER getUserById {}", id);
        return userService.get(id);
    }

    @GetMapping
    public Collection<UserDto> getAllUsers() {
        log.info("SERVER getAllUsers");
        return userService.getAll();
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.info("SERVER USER create");
        return userService.add(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@RequestBody UserDto userDto,
                          @PathVariable Long userId) throws NotFoundException {
        log.info("SERVER update userId {}", userId);
        return userService.patch(userDto, userId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
