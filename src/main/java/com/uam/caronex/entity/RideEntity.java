package com.uam.caronex.entity;

import com.uam.caronex.model.Location;
import com.uam.caronex.util.RideStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rides")
public class RideEntity {

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