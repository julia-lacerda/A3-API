package com.uam.caronex.service;

import com.uam.caronex.dto.NewRideRequest;
import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.mapper.RideMapper;
import com.uam.caronex.model.AddParticipantRequest;
import com.uam.caronex.model.RideModel;
import com.uam.caronex.model.UpdateRideRequest;
import com.uam.caronex.repository.RideRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RideServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private RideRepository rideRepository;

    @InjectMocks
    private RideService rideService;

    @Test
    public void shouldReturnRideResponse_WhenGetRide() {
            String id = "123";
            RideEntity rideEntity = new RideEntity();
            rideEntity.setId(id);
            RideResponse expectedResponse = RideMapper.toResponse(rideEntity);

            when(rideRepository.getRide(id)).thenReturn(rideEntity);

            RideResponse actualResponse = rideService.getRide(id);

            assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

    @Test
    public void shouldGetRidesList_WhenGetListOfRides() {
        // Mock input data
        RideRequest rideRequest = new RideRequest();
        RideEntity ride1 = new RideEntity();
        RideEntity ride2 = new RideEntity();
        List<RideEntity> expectedRides = Arrays.asList(ride1, ride2);
        List<RideResponse> expectedResponses = Arrays.asList(
                RideMapper.toResponse(ride1),
                RideMapper.toResponse(ride2)
        );

        when(rideRepository.getRides(any(RideModel.class))).thenReturn(expectedRides);

        List<RideResponse> actualResponses = rideService.getRides(rideRequest);

        assertEquals(expectedResponses.size(), actualResponses.size());
        for (int i = 0; i < expectedResponses.size(); i++) {
            RideResponse expectedResponse = expectedResponses.get(i);
            RideResponse actualResponse = actualResponses.get(i);
            assertEquals(expectedResponse.getId(), actualResponse.getId());
        }
    }

    @Test
    public void shouldReturnRide_WhenCreateRide() {
        NewRideRequest newRideRequest = new NewRideRequest();
        newRideRequest.setCpf("12345678910");
        UserResponse userResponse = new UserResponse();
        RideEntity rideEntity = new RideEntity();
        RideResponse expectedResponse = RideMapper.toResponse(rideEntity);

        when(userService.findUserById(newRideRequest.getCpf())).thenReturn(userResponse);
        when(rideRepository.createRide(any(RideEntity.class))).thenReturn(rideEntity);

        RideResponse actualResponse = rideService.createRide(newRideRequest);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

    @Test
    public void shouldGetAllRidesList_WhenUserIsDriver() {
        String cpf = "12345678910";
        UserResponse userResponse = new UserResponse();
        userResponse.setCnh("ABC123");
        RideEntity ride1 = new RideEntity();
        RideEntity ride2 = new RideEntity();
        List<RideEntity> expectedRideEntities = Arrays.asList(ride1, ride2);
        List<RideResponse> expectedResponses = Arrays.asList(
                RideMapper.toResponse(ride1),
                RideMapper.toResponse(ride2)
        );

        when(userService.findUserById(cpf)).thenReturn(userResponse);
        when(rideRepository.getAllRidesAsDriver(cpf)).thenReturn(expectedRideEntities);


        List<RideResponse> actualResponses = rideService.getAllRidesAsDriver(cpf);

        assertEquals(expectedResponses.size(), actualResponses.size());
        for (int i = 0; i < expectedResponses.size(); i++) {
            RideResponse expectedResponse = expectedResponses.get(i);
            RideResponse actualResponse = actualResponses.get(i);
            assertEquals(expectedResponse.getId(), actualResponse.getId());
        }
    }

    @Test
    public void shouldThrowException_WhenUserIsNotDriver() {
        String cpf = "12345678910";
        UserResponse userResponse = new UserResponse();

        when(userService.findUserById(cpf)).thenReturn(userResponse);

        assertThrows(RuntimeException.class, () -> rideService.getAllRidesAsDriver(cpf));
    }

    @Test
    public void shouldGetRidesList_WhenGetAllRidesAsUser() {
        String cpf = "12345678910";
        RideEntity ride1 = new RideEntity();
        RideEntity ride2 = new RideEntity();
        List<RideEntity> expectedRideEntities = Arrays.asList(ride1, ride2);
        List<RideResponse> expectedResponses = Arrays.asList(
                RideMapper.toResponse(ride1),
                RideMapper.toResponse(ride2)
        );

        when(rideRepository.getAllRidesAsUser(cpf)).thenReturn(expectedRideEntities);

        List<RideResponse> actualResponses = rideService.getAllRidesAsUser(cpf);

        assertEquals(expectedResponses.size(), actualResponses.size());
        for (int i = 0; i < expectedResponses.size(); i++) {
            RideResponse expectedResponse = expectedResponses.get(i);
            RideResponse actualResponse = actualResponses.get(i);
            assertEquals(expectedResponse.getId(), actualResponse.getId());
        }
    }

    @Test
    public void shouldUpdateRide_WhenUserUpdateARide() {
        String rideId = "ride123";
        UpdateRideRequest updateRideRequest = new UpdateRideRequest();
        NewRideRequest newRideRequest = new NewRideRequest();
        RideEntity rideEntity = new RideEntity();
        RideResponse expectedResponse = new RideResponse();

        updateRideRequest.setRideRequest(newRideRequest);

        when(rideRepository.getRide(updateRideRequest.getId())).thenReturn(rideEntity);
        when(rideRepository.updateRide(rideEntity)).thenReturn(expectedResponse);

        RideResponse actualResponse = rideService.updateRide(updateRideRequest);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void shouldAddParticipant_WhenUserJoinRide() {
        String rideId = "ride123";
        String userCpf = "user123";
        AddParticipantRequest addParticipantRequest = new AddParticipantRequest(rideId, userCpf);
        RideEntity rideEntity = new RideEntity();
        List<UserEntity> participantsList = new ArrayList<>();
        rideEntity.setParticipantsList(participantsList);
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = new UserEntity();
        RideResponse expectedResponse = new RideResponse();

        when(rideRepository.getRide(addParticipantRequest.getRideId())).thenReturn(rideEntity);
        when(userService.findUserById(addParticipantRequest.getUserCpf())).thenReturn(userResponse);
        when(rideRepository.updateRide(rideEntity)).thenReturn(expectedResponse);

        RideResponse actualResponse = rideService.addParticipant(addParticipantRequest);

        assertEquals(expectedResponse, actualResponse);
    }

}
