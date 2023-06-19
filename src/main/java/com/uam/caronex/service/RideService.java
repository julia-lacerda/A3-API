package com.uam.caronex.service;

import com.uam.caronex.dto.*;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.mapper.RideMapper;
import com.uam.caronex.mapper.UserMapper;
import com.uam.caronex.dto.AddParticipantRequest;
import com.uam.caronex.dto.UpdateRideRequest;
import com.uam.caronex.repository.RideRepository;
import com.uam.caronex.util.RideStatusEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RideService {

    private RideRepository rideRepository;

    private RideMapper rideMapper;

    private UserService userService;

    @Autowired
    public RideService(RideRepository rideRepository, UserService userService) {
        this.userService = userService;
        this.rideRepository = rideRepository;
    }

    public RideResponse getRide(String id) {
        RideEntity ride = rideRepository.getRide(id);
        return RideMapper.toResponse(ride);
    }

    public List<RideResponse> getRides(RideRequest rideRequest) {
        List<RideEntity> list = rideRepository.getRides(RideMapper.toModel(rideRequest));
        return list.stream().map(RideMapper::toResponse).toList();
    }

    public RideResponse createRide(NewRideRequest newRideRequest) {
        UserResponse user = userService.findUserById(newRideRequest.getCpf());
        RideEntity rideEntity = rideRepository.createRide(RideMapper.toEntity(newRideRequest, user));

       return RideMapper.toResponse(rideEntity);
    }

    public List<RideResponse> getAllRidesAsDriver(String cpf) {
        UserResponse user = userService.findUserById(cpf);
        if(user.getCnh() != null) {
            List<RideEntity> list = rideRepository.getAllRidesAsDriver(cpf);
            return list.stream().map(RideMapper::toResponse).toList();
        }else throw new RuntimeException("User is not a driver");
    }

    public List<RideResponse> getAllRidesAsUser(String cpf) {
        List<RideEntity> list = rideRepository.getAllRidesAsUser(cpf);
        return list.stream().map(RideMapper::toResponse).toList();
    }

    public RideResponse updateRide(UpdateRideRequest request) {
        RideEntity ride = rideRepository.getRide(request.getId());

        ride.setParticipantsList(request.getRideRequest().getParticipantsList());
        ride.setOrigin(request.getRideRequest().getOrigin());
        ride.setParticipantsList(request.getRideRequest().getParticipantsList());
        ride.setDestination(request.getRideRequest().getDestination());
        ride.setDateTime(request.getRideRequest().getDateTime());
        ride.setVehicle(request.getRideRequest().getVehicle());

        return rideRepository.updateRide(ride);
    }

    public RideResponse addParticipant(AddParticipantRequest request) {
        RideEntity ride = rideRepository.getRide(request.getRideId());

        if(ride.getParticipantsList() != null && ride.getParticipantsList().stream().anyMatch(user -> user.getCpf().equals(request.getUserCpf())))
            throw new RuntimeException("User is already a participant");

        UserResponse user = userService.findUserById(request.getUserCpf());
        ride.getParticipantsList().add(UserMapper.toEntity(user));
        return rideRepository.updateRide(ride);
    }


    public RideResponse removeParticipant(RemoveParticipantRequest request) {
        RideEntity ride = rideRepository.getRide(request.getRideId());

        // Verify if user is a participant of the ride
        if (ride.getParticipantsList() == null || ride.getParticipantsList().stream().noneMatch(user -> user.getCpf().equals(request.getUserCpf())))
           throw new RuntimeException("User is not a participant");

        ride.getParticipantsList().removeIf(user -> user.getCpf().equals(request.getUserCpf()));

        return rideRepository.updateRide(ride);
    }

    public RideResponse cancelRide(CancelRideRequest request) {
        UserResponse user = userService.findUserById(request.getUserCpf());

        // Verify if user that is trying to cancel is a driver
        if(!user.getIsDriver())
            throw new RuntimeException("User is not a driver. Only drivers can cancel a ride");

        RideEntity ride = rideRepository.getRide(request.getRideId());

        // Verify if ride is status available
        if(!ride.getStatus().equals(RideStatusEnum.AVAILABLE))
            throw new RuntimeException("This ride can not be canceled.");

        ride.setStatus(RideStatusEnum.CANCELED);

        return rideRepository.updateRide(ride);
    }

}