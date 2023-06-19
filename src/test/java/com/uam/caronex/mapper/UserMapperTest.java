package com.uam.caronex.mapper;

import com.uam.caronex.dto.NewUserRequest;
import com.uam.caronex.dto.UpdateUserRequest;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.entity.VehicleEntity;
import com.uam.caronex.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    @Test
    public void shouldMapUserEntityToUserResponse() {
        Account account = new Account();
        VehicleEntity vehicle1 = new VehicleEntity();
        VehicleEntity vehicle2 = new VehicleEntity();

        UserEntity userEntity = new UserEntity();
        userEntity.setId("12345");
        userEntity.setAccount(account);
        userEntity.setName("Julia");
        userEntity.setPhoneNumber("11912345678");
        userEntity.setBirthDate(LocalDate.of(2002,2,10));
        userEntity.setCpf("12345678910");
        userEntity.setCnh("1234567891011");
        userEntity.setIsDriver(true);
        userEntity.getVehicles().add(vehicle1);
        userEntity.getVehicles().add(vehicle2);

        UserResponse userResponse = UserMapper.toResponse(userEntity);
        userEntity.setId("12345");
        userEntity.setAccount(account);
        userEntity.setName("Julia");
        userEntity.setPhoneNumber("11912345678");
        userEntity.setBirthDate(LocalDate.of(2002,2,10));
        userEntity.setCpf("12345678910");
        userEntity.setCnh("1234567891011");
        userEntity.setIsDriver(true);
        userEntity.getVehicles().add(vehicle1);
        userEntity.getVehicles().add(vehicle2);

        assertEquals(userEntity.getId(), userResponse.getId());
        assertEquals(userEntity.getAccount(), userResponse.getAccount());
        assertEquals(userEntity.getName(), userResponse.getName());
        assertEquals(userEntity.getPhoneNumber(), userResponse.getPhoneNumber());
        assertEquals(userEntity.getBirthDate(), userResponse.getBirthDate());
        assertEquals(userEntity.getCpf(), userResponse.getCpf());
        assertEquals(userEntity.getCnh(), userResponse.getCnh());
        assertEquals(userEntity.getIsDriver(), userResponse.getIsDriver());
    }

    @Test
    public void shouldMapUserEntityToUserResponseWithNewUserRequest() {
        Account account = new Account();
        VehicleEntity vehicle1 = new VehicleEntity();
        VehicleEntity vehicle2 = new VehicleEntity();

        UserEntity userEntity = new UserEntity();
        userEntity.setId("12345");
        userEntity.setAccount(account);
        userEntity.setName("Julia");
        userEntity.setPhoneNumber("11912345678");
        userEntity.setBirthDate(LocalDate.of(2002,2,10));
        userEntity.setCpf("12345678910");
        userEntity.setCnh("1234567891011");
        userEntity.setIsDriver(true);
        userEntity.setVehicles(Arrays.asList(vehicle1, vehicle2));


        UserResponse userResponse = UserMapper.toResponse(userEntity);


        assertEquals(userEntity.getId(), userResponse.getId());
        assertEquals(userEntity.getAccount(), userResponse.getAccount());
        assertEquals(userEntity.getName(), userResponse.getName());
        assertEquals(userEntity.getPhoneNumber(), userResponse.getPhoneNumber());
        assertEquals(userEntity.getBirthDate(), userResponse.getBirthDate());
        assertEquals(userEntity.getCpf(), userResponse.getCpf());
        assertEquals(userEntity.getCnh(), userResponse.getCnh());
        assertEquals(userEntity.getIsDriver(), userResponse.getIsDriver());
    }

    @Test
    public void shouldMapNewUserRequestToUserEntity() {
        Account account = new Account();

        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setAccount(account);
        newUserRequest.setName("Julia");
        newUserRequest.setPhoneNumber("11912345678");
        newUserRequest.setBirthDate(LocalDate.of(2002,2,10));
        newUserRequest.setCpf("12345678910");
        newUserRequest.setCnh("12345678911");
        newUserRequest.setIsDriver(true);

        UserEntity userEntity = UserMapper.toEntity(newUserRequest);

        assertEquals(newUserRequest.getAccount(), userEntity.getAccount());
        assertEquals(newUserRequest.getName(), userEntity.getName());
        assertEquals(newUserRequest.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(newUserRequest.getBirthDate(), userEntity.getBirthDate());
        assertEquals(newUserRequest.getCpf(), userEntity.getCpf());
        assertEquals(newUserRequest.getCnh(), userEntity.getCnh());
        assertEquals(newUserRequest.getIsDriver(), userEntity.getIsDriver());
    }

    @Test
    public void shouldMapUpdateUserRequestToUserEntity() {
        String cpf = "testCpf";
        Account account = new Account();

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId("1a2b3c4d5e");
        updateUserRequest.setAccount(account);
        updateUserRequest.setName("Julia");
        updateUserRequest.setPhoneNumber("11912345678");
        updateUserRequest.setBirthDate(LocalDate.of(2002,2,10));
        updateUserRequest.setCnh("12345678911");
        updateUserRequest.setIsDriver(true);

        UserEntity userEntity = UserMapper.toEntity(cpf, updateUserRequest);

        assertEquals(updateUserRequest.getAccount(), userEntity.getAccount());
        assertEquals(updateUserRequest.getName(), userEntity.getName());
        assertEquals(updateUserRequest.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(updateUserRequest.getBirthDate(), userEntity.getBirthDate());
        assertEquals(cpf, userEntity.getCpf());
        assertEquals(updateUserRequest.getCnh(), userEntity.getCnh());
        assertEquals(updateUserRequest.getIsDriver(), userEntity.getIsDriver());
    }

    @Test
    public void shouldMapUserResponseToUserEntity() {
        Account account = new Account();
        VehicleEntity vehicle1 = new VehicleEntity();
        VehicleEntity vehicle2 = new VehicleEntity();

        UserResponse userResponse = new UserResponse();
        userResponse.setId("12345");
        userResponse.setAccount(account);
        userResponse.setName("Julia");
        userResponse.setPhoneNumber("11912345678");
        userResponse.setBirthDate(LocalDate.of(2002,2,10));
        userResponse.setCpf("12345678910");
        userResponse.setCnh("1234567891011");
        userResponse.setIsDriver(true);
        userResponse.setVehicles(Arrays.asList(vehicle1, vehicle2));

        UserEntity userEntity = UserMapper.toEntity(userResponse);

        assertEquals(userResponse.getId(), userEntity.getId());
        assertEquals(userResponse.getAccount(), userEntity.getAccount());
        assertEquals(userResponse.getName(), userEntity.getName());
        assertEquals(userResponse.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(userResponse.getBirthDate(), userEntity.getBirthDate());
        assertEquals(userResponse.getCpf(), userEntity.getCpf());
        assertEquals(userResponse.getCnh(), userEntity.getCnh());
        assertEquals(userResponse.getIsDriver(), userEntity.getIsDriver());
    }


}
