package com.uam.caronex.dto;

import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.model.Location;
import com.uam.caronex.entity.VehicleEntity;
import com.uam.caronex.util.RideStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RideResponse {
    @Id
    private String id;
    private UserEntity owner;
    private List<UserEntity> participantsList;
    private Location origin;
    private Location destination;
    private LocalDateTime dateTime;
    private VehicleEntity vehicle;
    private RideStatusEnum status;
}
