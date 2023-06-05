package com.uam.caronex.mapper;

import com.uam.caronex.dto.NewRideRequest;
import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.dto.UserResponse;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.model.NewRideModel;
import com.uam.caronex.model.RideModel;
import org.springframework.stereotype.Component;

@Component
public class RideMapper {

    public static RideModel toModel(RideRequest rideRequest) {
        System.out.println(rideRequest);

        return RideModel.builder()
                .cpf(rideRequest.getCpf())
                .destination(rideRequest.getDestination())
                .origin(rideRequest.getOrigin())
                .dateTime(rideRequest.getDateTime())
                .status(rideRequest.getStatus())
                .build();
    }

    public static RideResponse toResponse(RideEntity ride) {
        return RideResponse.builder()
                .id(ride.getId())
                .owner(ride.getOwner())
                .participantsList(ride.getParticipantsList())
                .origin(ride.getOrigin())
                .destination(ride.getDestination())
                .dateTime(ride.getDateTime())
                .vehicle(ride.getVehicle())
                .status(ride.getStatus())
                .build();
    }

    public static RideEntity toEntity(NewRideRequest newRideRequest, UserResponse user) {
        return RideEntity.builder()
                .owner(UserEntity.builder()
                                .id(user.getId())
                                .account(user.getAccount())
                                .name(user.getName())
                                .phoneNumber(user.getPhoneNumber())
                                .birthDate(user.getBirthDate())
                                .cpf(user.getCpf())
                                .cnh(user.getCnh())
                                .isDriver(user.getIsDriver())
                                .build())
                .origin(newRideRequest.getOrigin())
                .destination(newRideRequest.getDestination())
                .participantsList(newRideRequest.getParticipantsList())
                .dateTime(newRideRequest.getDateTime())
                .vehicle(newRideRequest.getVehicle())
                .status(newRideRequest.getStatus())
                .build();
    }


}
