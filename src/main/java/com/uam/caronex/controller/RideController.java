package com.uam.caronex.controller;

import com.uam.caronex.dto.NewRideRequest;
import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ride")
public class RideController {
    private final RideService rideService;
    ;
    @GetMapping("/getRides")
    public List<RideResponse> getRides(@RequestBody RideRequest rideRequest) {
        return rideService.getRides(rideRequest);
    }

    @PostMapping("/createRide")
    public RideResponse createRide(@RequestBody NewRideRequest newRideRequest){    return rideService.createRide(newRideRequest);
    }
}
