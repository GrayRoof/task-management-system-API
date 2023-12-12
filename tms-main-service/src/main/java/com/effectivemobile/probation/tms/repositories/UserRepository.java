package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.exceptions.NotFoundException;
import com.effectivemobile.probation.tms.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User get(long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Пользователь с идентификатором #" +
                id + " не зарегистрирован!"));
    }

    Optional<User> findByEmail(String username);
}
