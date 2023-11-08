package com.juliar.tehnoskytask.repository;

import com.juliar.tehnoskytask.IntegrationTestBase;
import com.juliar.tehnoskytask.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class UserRepositoryTest extends IntegrationTestBase {

    private static final Integer IVANOV_ID = 1;
    private static final String USER_IVANOV = "Ivanov I.I.";

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {

        Optional<User> user = userRepository.findById(IVANOV_ID);
        assertTrue(user.isPresent());
        user.ifPresent(entity -> assertEquals(USER_IVANOV, entity.getFullName()));
    }

    @Test
    void testFindByFullName() {
        Optional<User> userByFullName = userRepository.findByFullName(USER_IVANOV);
        assertTrue(userByFullName.isPresent());
    }
}