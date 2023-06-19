package com.uam.caronex.model;

import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.entity.VehicleEntity;
import com.uam.caronex.util.RideStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewRideModel {

    private UserEntity owner;
    private List<UserEntity> participantsList;
    private Location origin;
    private Location destination;
    private LocalDateTime dateTime;
    private VehicleEntity vehicle;
    private RideStatusEnum status;
}
