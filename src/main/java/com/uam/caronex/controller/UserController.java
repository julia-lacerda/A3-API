package com.uam.caronex.controller;

import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/findUserById")
    public UserResponse findUserById(String cpf) {
        return userService.findUserById(cpf);
    }
}
