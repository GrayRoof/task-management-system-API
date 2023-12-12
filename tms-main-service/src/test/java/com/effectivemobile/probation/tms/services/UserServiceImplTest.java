package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserDto;
import com.effectivemobile.probation.tms.model.user.UserMapper;
import com.effectivemobile.probation.tms.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    private final EntityManager entityManager;


    @Before
    public void setUp() {
        entityManager.createQuery("DELETE FROM User").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE users ALTER COLUMN id RESTART WITH 1").executeUpdate();
        entityManager.close();
    }

    @Test
    void shouldReturnUserById() {
        User localUser = new User();
        localUser.setId(1L);
        localUser.setName("First"); localUser.setEmail("first@test.test");
        localUser.setPassword("pass");
        User savedUser = userRepository.save(localUser);
        assertEquals(localUser.getName(), userService.get(savedUser.getId()).getName());
    }

    @Test
    void shouldReturnWhenGetAll() {
        User localFirstUser = new User();
        localFirstUser.setId(1L);
        localFirstUser.setName("First"); localFirstUser.setEmail("first@test.test");
        localFirstUser.setPassword("pass");

        User localSecondUser = new User();
        localSecondUser.setId(2L);
        localSecondUser.setName("Second"); localSecondUser.setEmail("second@test.test");
        localSecondUser.setPassword("pass");

        Collection<UserDto> expected = List.of(
                UserMapper.toDto(userRepository.save(localFirstUser)),
                UserMapper.toDto(userRepository.save(localSecondUser)));
        Collection<UserDto> actual = userService.getAll(0, 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyCollectionWhenGetAllWithNoData() {
        Collection<UserDto> expected = List.of();
        Collection<UserDto> actual = userService.getAll(0,10);
        Assertions.assertEquals(expected, actual);
    }

}