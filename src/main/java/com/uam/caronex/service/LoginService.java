package com.uam.caronex.service;

import com.uam.caronex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public boolean checkAccess(String email, String password){
        userRepository.checkAccess(email, password);
        return false;
    }
}
