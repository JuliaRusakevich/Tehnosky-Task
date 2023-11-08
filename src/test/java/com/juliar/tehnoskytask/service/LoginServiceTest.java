package com.juliar.tehnoskytask.service;

import com.juliar.tehnoskytask.dto.LoginDto;
import com.juliar.tehnoskytask.dto.UserReadDto;
import com.juliar.tehnoskytask.entity.User;
import com.juliar.tehnoskytask.repository.UserRepository;
import org.apache.el.stream.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    private static final String TESTING_EMAIL = "ivanov@gmail.com";
    private static final String TESTING_PASSWORD = "123";

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

/*
    @Test
    void testFindByEmail() {
        doReturn(Optional.Of(new User(TESTING_EMAIL, TESTING_PASSWORD)))
                .when(userRepository).findByMail(TESTING_EMAIL);
        var actualResult = loginService.findByMail(new LoginDto(TESTING_EMAIL, TESTING_PASSWORD));
        var expectedResult = new UserReadDto(TESTING_EMAIL);
        Assertions.assertEquals(actualResult, expectedResult);
    }

 */
}