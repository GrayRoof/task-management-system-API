package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.user.LoginUserDto;
import com.effectivemobile.probation.tms.model.user.RegisterUserDto;
import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрирует нового Пользователя
     * @param input данные Пользователя для регистрации
     * @return User
     */
    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        User newUser = userRepository.save(user);
        log.info("Пользователь создан с идентификатором #{}", newUser.getId());
        return newUser;
    }

    /**
     * Выполняет аутентификацию зарегистрированного Пользователя
     * @param input данные Пользователя для аутентификации
     * @return User
     */
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}

