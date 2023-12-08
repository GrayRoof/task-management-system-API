package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
