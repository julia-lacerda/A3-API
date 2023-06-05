package com.uam.caronex.dto;

import com.uam.caronex.model.Location;
import com.uam.caronex.util.RideStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RideRequest {

    private String cpf;
    private Double[] origin;
    private Double[] destination;
    private LocalDateTime dateTime;
    private RideStatusEnum status;
}
