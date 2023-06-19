package com.uam.caronex.mapper;

import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .account(userEntity.getAccount())
                .name(userEntity.getName())
                .phoneNumber(userEntity.getPhoneNumber())
                .birthDate(userEntity.getBirthDate())
                .cpf(userEntity.getCpf())
                .cnh(userEntity.getCnh())
                .isDriver(userEntity.getIsDriver())
                .build();
    }

    public static UserEntity toEntity(UserResponse userResponse) {
        return UserEntity.builder()
                .id(userResponse.getId())
                .account(userResponse.getAccount())
                .name(userResponse.getName())
                .phoneNumber(userResponse.getPhoneNumber())
                .birthDate(userResponse.getBirthDate())
                .cpf(userResponse.getCpf())
                .cnh(userResponse.getCnh())
                .isDriver(userResponse.getIsDriver())
                .build();

    }
}
