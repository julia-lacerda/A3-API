package com.uam.caronex.controller;

import com.uam.caronex.dto.NewUserRequest;
import com.uam.caronex.dto.UpdateUserRequest;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{cpf}")
    public UserResponse getByCpf(@PathVariable String cpf) {
        return userService.findUserById(cpf);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody NewUserRequest request) {
        return userService.createUser(request);
    }


    @PutMapping("/{cpf}")
    public UserResponse updateUser(@PathVariable String cpf, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(cpf, request);
    }

    @DeleteMapping("/{cpf}")
    public void deleteUser(String cpf) {
        userService.deleteUser(cpf);
    }
}
