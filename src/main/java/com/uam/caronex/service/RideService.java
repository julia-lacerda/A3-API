package com.uam.caronex.service;

import com.uam.caronex.dto.NewRideRequest;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.mapper.RideMapper;
import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.repository.RideRepository;
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
}
