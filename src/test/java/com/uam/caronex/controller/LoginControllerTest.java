package com.uam.caronex.controller;

import com.uam.caronex.repository.UserRepository;
import com.uam.caronex.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrue_WhenHasAccount() {
        String email = "abc123@example.com";
        String password = "abc1234567890#";

        when(loginService.checkAccess(email, password)).thenReturn(true);

        boolean response = loginController.loginAuthenticator(email, password);
        assertTrue(response);
    }


}
