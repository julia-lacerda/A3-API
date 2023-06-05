package com.uam.caronex.dto;

import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.model.Location;
import com.uam.caronex.model.Vehicle;
import com.uam.caronex.util.RideStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewRideRequest {

    private String cpf;
    private Location origin;
    private Location destination;
    private List<UserEntity> participantsList;
    private LocalDateTime dateTime;
    private Vehicle vehicle;
    private RideStatusEnum status;

}
