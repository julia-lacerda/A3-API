package com.uam.caronex.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
        private String id;
        private String model;
        private String color;
        private String plate;
        private Integer places;
}

