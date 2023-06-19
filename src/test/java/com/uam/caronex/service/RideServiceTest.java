package com.uam.caronex.service;

import com.uam.caronex.dto.*;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.mapper.RideMapper;
import com.uam.caronex.model.RideModel;
import com.uam.caronex.repository.RideRepository;
import com.uam.caronex.util.RideStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void shouldRemoveParticipant_WhenParticipantExists() {
        // Given
        UserEntity participant = new UserEntity();
        participant.setCpf("12345678910");
        RemoveParticipantRequest request = new RemoveParticipantRequest("1234", "12345678910");
        RideEntity ride = new RideEntity();
        ride.setId("12364");
        List<UserEntity> participantsList = new ArrayList<>(List.of(participant));
        ride.setParticipantsList(participantsList);

        when(rideRepository.getRide(anyString())).thenReturn(ride);

        // When
        RideResponse response = rideService.removeParticipant(request);

        // Then
        verify(rideRepository).updateRide(ride);
        assertEquals(0, ride.getParticipantsList().size());
    }

    @Test
    void shouldThrowRuntimeException_WhenParticipantDoesNotExist() {

        UserEntity participant = new UserEntity();
        participant.setCpf("1457878959");
        RemoveParticipantRequest request = new RemoveParticipantRequest("1234", "123645678910");
        RideEntity ride = new RideEntity();
        ride.setId("1234");
        List<UserEntity> participantsList = new ArrayList<>(List.of(participant));

        ride.setParticipantsList(participantsList);

        when(rideRepository.getRide("rideId")).thenReturn(ride);

        // When/Then
        assertThrows(RuntimeException.class, () -> rideService.removeParticipant(request));
    }


    @Test
    void shouldReturnRideResponse_WhenCancelRide() {
        String userCpf = "123456789";
        String rideId = "ride123";

        UserResponse driverUser = new UserResponse();
        driverUser.setName("Julia");
        driverUser.setCpf(userCpf);
        driverUser.setIsDriver(true);

        RideEntity ride = new RideEntity();
        ride.setId(rideId);
        ride.setStatus(RideStatusEnum.AVAILABLE);

        RideResponse expectedResponse = new RideResponse();
        expectedResponse.setId(rideId);
        expectedResponse.setStatus(RideStatusEnum.CANCELED);

        when(userService.findUserById(anyString())).thenReturn(driverUser);
        when(rideRepository.getRide(anyString())).thenReturn(ride);

        doReturn(expectedResponse).when(rideRepository).updateRide(ride);


        RideResponse actualResponse = rideService.cancelRide(new CancelRideRequest(userCpf, rideId));

        assertEquals(expectedResponse, actualResponse);
        assertEquals(RideStatusEnum.CANCELED, ride.getStatus());
    }

    @Test
    void shouldThrowRuntimeException_WhenCancelRideWithNonDriverUser() {
        // Given
        String userCpf = "123456789";
        String rideId = "1234";
        UserResponse nonDriverUser = new UserResponse();
        nonDriverUser.setName("Alessandra");
        nonDriverUser.setCpf(userCpf);
        nonDriverUser.setIsDriver(false);

        when(userService.findUserById(userCpf)).thenReturn(nonDriverUser);

        // When/Then
        assertThrows(RuntimeException.class, () -> rideService.cancelRide(new CancelRideRequest(userCpf, rideId)));
    }

    @Test
    void shouldThrowRuntimeException_WhenCancelNonAvailableRide() {
        // Given
        String userCpf = "123456789";
        String rideId = "ride123";
        UserResponse driverUser = new UserResponse();
        driverUser.setName("Alessandra");
        driverUser.setCpf(userCpf);
        driverUser.setIsDriver(false);

        RideEntity ride = new RideEntity();
        ride.setId(rideId);
        ride.setStatus(RideStatusEnum.CLOSED);

        when(userService.findUserById(userCpf)).thenReturn(driverUser);
        when(rideRepository.getRide(rideId)).thenReturn(ride);

        // When/Then
        assertThrows(RuntimeException.class, () -> rideService.cancelRide(new CancelRideRequest(userCpf, rideId)));
    }

}
