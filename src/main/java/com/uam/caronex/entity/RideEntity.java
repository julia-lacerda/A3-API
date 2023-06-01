package com.uam.caronex.entity;

import com.uam.caronex.model.Location;
import com.uam.caronex.model.Vehicle;
import com.uam.caronex.util.RideStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Builder
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
    private Vehicle vehicle;
    private RideStatusEnum status;


}