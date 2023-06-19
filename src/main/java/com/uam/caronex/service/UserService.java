package com.uam.caronex.service;

import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.mapper.RideMapper;
import com.uam.caronex.mapper.UserMapper;
import com.uam.caronex.repository.RideRepository;
import com.uam.caronex.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private UserService rideService;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;}
   public UserResponse findUserById(String cpf) {
        UserEntity user = userRepository.findUserById(cpf);
       System.out.println(user);

       return UserMapper.toResponse(user);
    };

}
