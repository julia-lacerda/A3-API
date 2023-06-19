package com.uam.caronex.controller;

import com.uam.caronex.dto.*;
import com.uam.caronex.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ride")
public class RideController {
    private final RideService rideService;

    @GetMapping("/getRides")
    public List<RideResponse> getRides(@RequestBody RideRequest rideRequest) {
        return rideService.getRides(rideRequest);
    }

    @GetMapping("/getAllRides/asDriver")
    public List<RideResponse> getAllRidesAsDriver(String cpf) {
        return rideService.getAllRidesAsDriver(cpf);
    }
    @GetMapping("/getAllRides/asUser")
    public List<RideResponse> getAllRidesAsUser(String cpf) {
        return rideService.getAllRidesAsUser(cpf);
    }

    @PostMapping("/createRide")
    public RideResponse createRide(@RequestBody NewRideRequest newRideRequest){    return rideService.createRide(newRideRequest);
    }

    @PostMapping("/updateRide")
    public RideResponse updateRide(@RequestBody UpdateRideRequest request){
        return rideService.updateRide(request);
    }

    @PostMapping("/addParticipant")
    public RideResponse addParticipant(@RequestBody AddParticipantRequest request){
        return rideService.addParticipant(request);
    }

    @PostMapping("/removeParticipant")
    public RideResponse removeParticipant(@RequestBody RemoveParticipantRequest request){
        return rideService.removeParticipant(request);
    }

    @PostMapping("/cancelRide")
    public RideResponse cancelRide(@RequestBody CancelRideRequest request) {
        return rideService.cancelRide(request);
    }

}
