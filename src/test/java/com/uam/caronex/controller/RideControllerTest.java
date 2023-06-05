package com.uam.caronex.controller;

import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.mapper.RideMapper;
import com.uam.caronex.model.Account;
import com.uam.caronex.model.Location;
import com.uam.caronex.model.Vehicle;
import com.uam.caronex.repository.RideRepository;
import com.uam.caronex.service.RideService;
import com.uam.caronex.util.RideStatusEnum;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RideControllerTest {

    @Mock
    private RideService rideService;

    @Mock
    private RideRepository  rideRepository;

    @InjectMocks
    private RideController rideController;
    @Test
    void Should_ReturnRideResponse_When_GetRides() {
        RideRequest rideRequest = new RideRequest();
        UserEntity user = new UserEntity();
        RideResponse rideResponse = new RideResponse();
        List<RideResponse> rideResponses = List.of(rideResponse);

        Mockito.when(rideService.getRides(rideRequest)).thenReturn(rideResponses);

        List<RideResponse> result = rideController.getRides(rideRequest);

        assertEquals(rideResponses, result);
    }

}
