package com.uam.caronex.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {
    @Id
    private String id;

    private String model;
    private String color;

    @Pattern(regexp = "^[a-zA-Z]{3}\\d[A-Za-z0-9]\\d{2}$")
    private String plate;

    private Integer places;
}
