package com.uam.caronex.mapper;

import com.uam.caronex.dto.NewRideRequest;
import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.entity.VehicleEntity;
import com.uam.caronex.model.Account;
import com.uam.caronex.model.Location;
import com.uam.caronex.model.RideModel;
import com.uam.caronex.util.RideStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RideMapperTest {


    @Test
    public void shouldMapRideRequestToRideModel() {
        Double[] destination = {1.23, 4.56};
        Double[] origin = {7.89, 0.12};
        RideRequest rideRequest = new RideRequest();
        rideRequest.setCpf("123456789");
        rideRequest.setDestination(destination);
        rideRequest.setOrigin(origin);
        LocalDateTime dateTime = LocalDateTime.of(2023, 6, 18, 10, 0, 0);
        rideRequest.setDateTime(dateTime);
        rideRequest.setStatus(RideStatusEnum.valueOf("AVAILABLE"));

        RideModel rideModel = RideMapper.toModel(rideRequest);

        assertEquals(rideRequest.getCpf(), rideModel.getCpf());
        assertEquals(rideRequest.getDestination(), rideModel.getDestination());
        assertEquals(rideRequest.getOrigin(), rideModel.getOrigin());
        assertEquals(rideRequest.getDateTime(), rideModel.getDateTime());
        assertEquals(rideRequest.getStatus(), rideModel.getStatus());
    }

    @Test
    public void shouldMapRideEntityToRideResponse() {
        // Given
        UserEntity userEntity = new UserEntity();
        UserEntity participant1 = new UserEntity();
        UserEntity participant2 = new UserEntity();
        VehicleEntity vehicleEntity = new VehicleEntity();
        Location location = new Location();
        RideEntity rideEntity = new RideEntity();

        rideEntity.setId("12345");
        rideEntity.setOwner(userEntity);
        rideEntity.setParticipantsList(Arrays.asList(participant1, participant2));
        rideEntity.setOrigin(location);
        rideEntity.setDestination(location);
        LocalDateTime dateTime = LocalDateTime.of(2023, 6, 18, 10, 0, 0);
        rideEntity.setDateTime(dateTime);
        rideEntity.setVehicle(vehicleEntity);
        rideEntity.setStatus(RideStatusEnum.valueOf("AVAILABLE"));

        // When
        RideResponse rideResponse = RideMapper.toResponse(rideEntity);

        // Then
        assertEquals(rideEntity.getId(), rideResponse.getId());
        assertEquals(rideEntity.getOwner(), rideResponse.getOwner());
        assertEquals(rideEntity.getParticipantsList(), rideResponse.getParticipantsList());
        assertEquals(rideEntity.getOrigin().getCoordinates(), rideResponse.getOrigin().getCoordinates());
        assertEquals(rideEntity.getOrigin().getLabel(), rideResponse.getOrigin().getLabel());
        assertEquals(rideEntity.getDestination().getCoordinates(), rideResponse.getDestination().getCoordinates());
        assertEquals(rideEntity.getDestination().getLabel(), rideResponse.getDestination().getLabel());
        assertEquals(rideEntity.getDateTime(), rideResponse.getDateTime());
        assertEquals(rideEntity.getVehicle(), rideResponse.getVehicle());
        assertEquals(rideEntity.getStatus(), rideResponse.getStatus());
    }

    @Test
    public void shouldMapNewRideRequestToRideEntity() {
        Location location = new Location();
        UserEntity participant1 = new UserEntity();
        UserEntity participant2 = new UserEntity();
        VehicleEntity vehicleEntity = new VehicleEntity();
        Account account = new Account();

        NewRideRequest newRideRequest = new NewRideRequest();
        newRideRequest.setCpf("12345678910");
        newRideRequest.setOrigin(location);
        newRideRequest.setDestination(location);
        newRideRequest.setParticipantsList(Arrays.asList(participant1, participant2));
        newRideRequest.setDateTime(LocalDateTime.of(2023, 6, 18, 10, 0, 0));
        newRideRequest.setVehicle(vehicleEntity);
        newRideRequest.setStatus(RideStatusEnum.valueOf("AVAILABLE"));


        UserResponse userResponse = new UserResponse();
        userResponse.setId("1a2b3c4d5e");
        userResponse.setAccount(account);
        userResponse.setName("John Doe");
        userResponse.setPhoneNumber("11912345678");
        userResponse.setBirthDate(LocalDate.of(2002, 2,10));
        userResponse.setCpf("12345678910");
        userResponse.setCnh("12345678910");
        userResponse.setIsDriver(true);

        RideEntity rideEntity = RideMapper.toEntity(newRideRequest, userResponse);

        assertEquals(userResponse.getId(), rideEntity.getOwner().getId());
        assertEquals(userResponse.getAccount(), rideEntity.getOwner().getAccount());
        assertEquals(userResponse.getName(), rideEntity.getOwner().getName());
        assertEquals(userResponse.getPhoneNumber(), rideEntity.getOwner().getPhoneNumber());
        assertEquals(userResponse.getBirthDate(), rideEntity.getOwner().getBirthDate());
        assertEquals(userResponse.getCpf(), rideEntity.getOwner().getCpf());
        assertEquals(userResponse.getCnh(), rideEntity.getOwner().getCnh());
        assertEquals(userResponse.getIsDriver(), rideEntity.getOwner().getIsDriver());
        assertEquals(newRideRequest.getOrigin(), rideEntity.getOrigin());
        assertEquals(newRideRequest.getDestination(), rideEntity.getDestination());
        assertEquals(newRideRequest.getParticipantsList(), rideEntity.getParticipantsList());
        assertEquals(newRideRequest.getDateTime(), rideEntity.getDateTime());
        assertEquals(newRideRequest.getVehicle(), rideEntity.getVehicle());
        assertEquals(newRideRequest.getStatus(), rideEntity.getStatus());
    }
}
