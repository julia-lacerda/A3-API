package com.uam.caronex.service;

import com.uam.caronex.dto.VehicleRequest;
import com.uam.caronex.dto.NewUserRequest;
import com.uam.caronex.dto.UpdateUserRequest;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.dto.VehicleResponse;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.entity.VehicleEntity;
import com.uam.caronex.mapper.UserMapperTest;
import com.uam.caronex.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    UserMapperTest userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldFindUserById_WhenGetUserByCpf() {
        String cpf = "123456789";
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setId("1");

        when(userRepository.findUserById(anyString())).thenReturn(userEntity);

        UserResponse actualResponse = userService.findUserById(cpf);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }


    @Test
    public void shouldAddUserOnDatabase_WhenCreateUser() {
        NewUserRequest request = new NewUserRequest();
        UserEntity userEntity = new UserEntity();
        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setId("1");

        doAnswer(invocation -> {
            UserEntity savedUser = invocation.getArgument(0);
            savedUser.setId("1");
            return null;
        }).when(userRepository).create(any(UserEntity.class));

        UserResponse actualResponse = userService.createUser(request);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertNotNull(actualResponse.getId());
    }

    @Test
    public void shouldThrowException_WhenUserNotFound() {

        String cpf = "123456789";
        UpdateUserRequest request = new UpdateUserRequest();
        UserEntity userEntity = new UserEntity();

        doReturn(null).when(userRepository).findUserById(anyString());

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(cpf, request);
        });

    }

    @Test
    public void shouldUpdateUserOnDatabase_WhenUpdateUser() {
        String cpf = "123456789";
        UpdateUserRequest request = new UpdateUserRequest();
        UserEntity userEntity = new UserEntity();
        UserResponse expectedResponse = new UserResponse();

        doReturn(userEntity).when(userRepository).findUserById(anyString());
        doReturn(userEntity).when(userRepository).update(any(UserEntity.class));

        UserResponse actualResponse = userService.updateUser(cpf, request);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

    @Test
    public void shouldDeleteUser() {
        String cpf = "123456789";

        userService.deleteUser(cpf);

        verify(userRepository, times(1)).deleteById(eq(cpf));
    }


    @Test
    public void shouldThrowException_WhenUserNotFoundWheAddVehicle() {
        String cpf = "123456789";
        VehicleRequest request = new VehicleRequest();

        when(userRepository.findUserById(eq(cpf))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            userService.addVehicle(cpf, request);
        });

        verify(userRepository, never()).update(any());
    }

    @Test
    public void shouldAddVehicleToUser_WhenAddVehicle() {
        // Mock input data
        String cpf = "123456789";
        VehicleRequest request = new VehicleRequest();
        UserEntity userEntity = new UserEntity();
        VehicleEntity vehicleEntity = new VehicleEntity();
        VehicleResponse expectedResponse = new VehicleResponse();

        when(userRepository.findUserById(eq(cpf))).thenReturn(userEntity);
        when(userRepository.update(eq(userEntity))).thenReturn(userEntity);

        VehicleResponse actualResponse = userService.addVehicle(cpf, request);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

    @Test
    public void shouldThrowException_WhenUserNotFoundWhenUpdateVehicle() {
        String cpf = "123456789";
        String vehicleId = "vehicle-1";
        VehicleRequest request = new VehicleRequest();

        when(userRepository.findUserById(eq(cpf))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            userService.updateVehicle(cpf, vehicleId, request);
        });

        verify(userRepository, never()).update(any());
    }

    @Test
    public void shouldThrowException_WhenVehicleNotFoundWhenUpdateUser() {
        // Mock input data
        String cpf = "123456789";
        String vehicleId = "vehicle-1";
        VehicleRequest request = new VehicleRequest();
        UserEntity userEntity = new UserEntity();
        userEntity.setVehicles(new ArrayList<>());

        // Mock findUserById() to return userEntity
        when(userRepository.findUserById(eq(cpf))).thenReturn(userEntity);

        // Verify that an exception is thrown when vehicle is not found
        assertThrows(RuntimeException.class, () -> {
            userService.updateVehicle(cpf, vehicleId, request);
        });

        // Verify that userRepository.update() is not called
        verify(userRepository, never()).update(any());
    }

    @Test
    public void shouldUpdateVehicleOnDatabase_WhenUpdateVehicle() {
        String cpf = "123456789";
        String plate = "ABC1234";
        VehicleRequest request = new VehicleRequest();
        UserEntity userEntity = new UserEntity();
        VehicleEntity existingVehicleEntity = new VehicleEntity();
        existingVehicleEntity.setPlate(plate);
        userEntity.setVehicles(new ArrayList<>(List.of(existingVehicleEntity)));
        VehicleEntity updatedVehicleEntity = new VehicleEntity();
        updatedVehicleEntity.setPlate(plate);
        VehicleResponse expectedResponse = new VehicleResponse();

        when(userRepository.findUserById(anyString())).thenReturn(userEntity);
        when(userRepository.update(any(UserEntity.class))).thenReturn(userEntity);

        VehicleResponse actualResponse = userService.updateVehicle(cpf, plate, request);

        assertEquals(expectedResponse.getPlate(), actualResponse.getPlate());
    }

    @Test
    public void shouldThrowException_WhenUserNotFoundWhenDeleteVehicle() {
        String cpf = "123456789";
        String vehicleId = "vehicle-1";

        when(userRepository.findUserById(eq(cpf))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            userService.deleteVehicle(cpf, vehicleId);
        });

        verify(userRepository, never()).update(any());
    }

    @Test
    public void shouldThrowException_WhenVehicleNotFound() {
        String cpf = "123456789";
        String vehicleId = "vehicle-1";
        UserEntity userEntity = new UserEntity();
        userEntity.setVehicles(new ArrayList<>());

        when(userRepository.findUserById(eq(cpf))).thenReturn(userEntity);

        assertThrows(RuntimeException.class, () -> {
            userService.deleteVehicle(cpf, vehicleId);
        });

        verify(userRepository, never()).update(any());
    }

    @Test
    public void shouldDeleteVehicleOnDatabase_WhenDeleteVehicle() {
        // Mock input data
        String cpf = "123456789";
        String vehicleId = "vehicle-1";
        UserEntity userEntity = new UserEntity();
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(vehicleId);
        List<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(vehicleEntity);
        userEntity.setVehicles(vehicles);

        when(userRepository.findUserById(eq(cpf))).thenReturn(userEntity);
        when(userRepository.update(eq(userEntity))).thenReturn(userEntity);

        userService.deleteVehicle(cpf, vehicleId);

        assertFalse(userEntity.getVehicles().stream()
                .anyMatch(v -> v.getId().equals(vehicleId)));
    }
}
