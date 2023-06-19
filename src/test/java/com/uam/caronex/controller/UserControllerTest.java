package com.uam.caronex.controller;

import com.uam.caronex.dto.*;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.repository.UserRepository;
import com.uam.caronex.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldReturnUserResponse_WhenGetUserByCpf() {
        String cpf = "12345678900";
        UserResponse userResponse = new UserResponse();

        when(userService.findUserById(cpf)).thenReturn(userResponse);

        UserResponse result = userController.getByCpf(cpf);

        assertEquals(userResponse, result);
    }

    @Test
    public void shouldReturnUser_WhenCreateUser() {
        // Mock input data
        NewUserRequest newUserRequest = new NewUserRequest();
        UserEntity userEntity = new UserEntity();
        UserResponse expectedResponse = new UserResponse();

        when(userService.createUser(newUserRequest)).thenReturn(expectedResponse);

        UserResponse actualResponse = userService.createUser(newUserRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldUpdateUser() {
        String cpf = "12345678910";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserResponse expectedResponse = new UserResponse();

        when(userService.updateUser(cpf, updateUserRequest)).thenReturn(expectedResponse);

        UserResponse actualResponse = userController.updateUser(cpf, updateUserRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldDeleteUser() {
        String cpf = "123456789";

        userController.deleteUser(cpf);
    }

}
