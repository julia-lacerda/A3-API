package com.uam.caronex.controller;

import com.uam.caronex.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/login")
public class LoginController {

    private LoginService loginService;

    @GetMapping
    public boolean loginAuthenticator(@PathVariable String email, String password) {
        return loginService.checkAccess(email, password);
    }
}
