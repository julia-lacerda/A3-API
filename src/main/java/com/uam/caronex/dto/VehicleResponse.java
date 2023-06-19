package com.uam.caronex.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private String id;
    private String model;
    private String color;
    private String plate;
    private Integer places;
}
