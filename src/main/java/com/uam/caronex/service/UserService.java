package com.uam.caronex.service;

import com.uam.caronex.dto.VehicleRequest;
import com.uam.caronex.dto.NewUserRequest;
import com.uam.caronex.dto.UpdateUserRequest;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.dto.VehicleResponse;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.entity.VehicleEntity;
import com.uam.caronex.mapper.UserMapper;
import com.uam.caronex.mapper.VehicleMapper;
import com.uam.caronex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;}

   public UserResponse findUserById(String cpf) {
       UserEntity user = userRepository.findUserById(cpf);

       return UserMapper.toResponse(user);
    }

    public UserResponse createUser(NewUserRequest request) {
        UserEntity user = UserMapper.toEntity(request);
        userRepository.create(user);
        return UserMapper.toResponse(user);
    }

    public UserResponse updateUser(String cpf, UpdateUserRequest request) {
        if(findUserById(cpf) == null) {
            throw new RuntimeException("User not found");
        }

        UserEntity user = UserMapper.toEntity(cpf, request);
        userRepository.update(user);
        return UserMapper.toResponse(user);
    }

    public void deleteUser(String cpf) {
        userRepository.deleteById(cpf);
    }

    public VehicleResponse addVehicle(String cpf, VehicleRequest request) {
        UserEntity user = userRepository.findUserById(cpf);

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        VehicleEntity vehicleEntity = VehicleMapper.toEntity(request);
        user.getVehicles().add(vehicleEntity);
        userRepository.update(user);

        return VehicleMapper.toResponse(vehicleEntity);
    }

    public VehicleResponse updateVehicle(String cpf, String vehicleId, VehicleRequest request) {
        UserEntity user = userRepository.findUserById(cpf);
        if(user == null) {
            throw new RuntimeException("User not found");
        }

        if(user.getVehicles().stream()
                .noneMatch(v -> v.getId().equals(vehicleId)))
                throw new RuntimeException("Vehicle not found");

        VehicleEntity entity = VehicleMapper.toEntity(vehicleId, request);
        user.getVehicles().removeIf(vehicle -> vehicle.getId().equals(vehicleId));
        user.getVehicles().add(entity);
        userRepository.update(user);

        return VehicleMapper.toResponse(entity);
    }

    public void deleteVehicle(String cpf, String vehicleId) {
        UserEntity user = userRepository.findUserById(cpf);
        if(user == null) {
            throw new RuntimeException("User not found");
        }

        if(user.getVehicles().stream()
                .noneMatch(v -> v.getId().equals(vehicleId)))
                throw new RuntimeException("Vehicle not found");

        user.getVehicles().removeIf(vehicle -> vehicle.getId().equals(vehicleId));
        userRepository.update(user);
    }
}
