package com.uam.caronex.mapper;

import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.entity.RideEntity;
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
}
