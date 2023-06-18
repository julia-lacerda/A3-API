package com.uam.caronex.model;

import com.uam.caronex.dto.NewRideRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRideRequest {
    private String id;
    private NewRideRequest rideRequest;
}
