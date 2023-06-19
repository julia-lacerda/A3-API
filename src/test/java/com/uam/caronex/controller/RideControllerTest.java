package com.uam.caronex.controller;

import com.uam.caronex.dto.*;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.repository.RideRepository;
import com.uam.caronex.service.RideService;
import com.uam.caronex.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RideControllerTest {

    @Mock
    RideRepository rideRepository;
    @Mock
    private RideService rideService;

    @Mock
    UserService userService;
    @InjectMocks
    private RideController rideController;

    @Test
    void shouldReturnRideResponse_WhenGetRides() {
        RideRequest rideRequest = new RideRequest();
        UserEntity user = new UserEntity();
        RideResponse rideResponse = new RideResponse();
        List<RideResponse> rideResponses = List.of(rideResponse);

        when(rideService.getRides(rideRequest)).thenReturn(rideResponses);

        List<RideResponse> result = rideController.getRides(rideRequest);

        assertEquals(rideResponses, result);
    }

    @Test
    void shouldReturnRideResponse_WhenCreateRide() {
        NewRideRequest newRideRequest = new NewRideRequest();
        RideResponse rideResponse = new RideResponse();
        UserResponse user = new UserResponse();

        when(rideService.createRide(newRideRequest)).thenReturn(rideResponse);

        RideResponse result = rideController.createRide(newRideRequest);

        assertEquals(rideResponse, result);
    }

    @Test
    void shouldReturnRideResponseList_WhenGetAllRidesAsDriver() {
        String cpf = "12345678910";
        RideResponse response = new RideResponse();

        when(rideService.getAllRidesAsDriver(cpf)).thenReturn(List.of(response));

        List<RideResponse> result = rideController.getAllRidesAsDriver(cpf);

        assertEquals(List.of(response), result);
    }

    @Test
    void shouldReturnRideResponseList_WhenGetAllRidesAsUser() {
        String cpf = "12345678910";
        RideResponse response = new RideResponse();

        when(rideService.getAllRidesAsDriver(cpf)).thenReturn(List.of(response));

        List<RideResponse> result = rideController.getAllRidesAsDriver(cpf);

        assertEquals(List.of(response), result);
    }

    @Test
    public void shouldReturnUpdatedRideResponse_WhenUpdateRide() {
        UpdateRideRequest request = new UpdateRideRequest();
        RideResponse expectedResponse = RideResponse.builder().id("123").build();

        when(rideService.updateRide(request)).thenReturn(expectedResponse);

        RideResponse actualResponse = rideController.updateRide(request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldAddParticipant_WhenUserJoinRide() {
        AddParticipantRequest request = new AddParticipantRequest();
        RideResponse expectedResponse = RideResponse.builder().id("a1b2c23").build();

        when(rideService.addParticipant(request)).thenReturn(expectedResponse);

        RideResponse actualResponse = rideController.addParticipant(request);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

    @Test
    public void shouldRemoveParticipant_WhenUserCancelRide() {
        RemoveParticipantRequest request = new RemoveParticipantRequest();
        RideResponse expectedResponse = RideResponse.builder().id("a1b2c23").build();

        when(rideService.removeParticipant(request)).thenReturn(expectedResponse);

        RideResponse actualResponse = rideController.removeParticipant(request);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

    @Test
    public void shouldUpdateRide_WhenDriverCancelRide() {
        CancelRideRequest request = new CancelRideRequest();
        RideResponse expectedResponse = RideResponse.builder().id("a1b2c23").build();

        when(rideService.cancelRide(request)).thenReturn(expectedResponse);

        RideResponse actualResponse = rideController.cancelRide(request);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

}
