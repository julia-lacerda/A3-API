package com.uam.caronex.service;

import com.uam.caronex.controller.UserController;
import com.uam.caronex.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    LoginService loginService;


    @Test
    public void shouldCheckAccess() {
        // Mock input data
        String email = "test@example.com";
        String password = "password123";

        // Call the controller method
        loginService.checkAccess(email, password);

        // Verify that userRepository.checkAccess() was called with the correct parameters
        verify(userRepository, times(1)).checkAccess(eq(email), eq(password));
    }

}
