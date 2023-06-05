package com.uam.caronex.controller;

import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void Should_ReturnUserResponse_When_FindUserById() {
        String cpf = "12345678900";
        UserResponse userResponse = new UserResponse();

        Mockito.when(userService.findUserById(cpf)).thenReturn(userResponse);

        UserResponse result = userController.findUserById(cpf);

        assertEquals(userResponse, result);
    }
}
