package com.uam.caronex.model;

import lombok.*;
import javax.validation.constraints.Pattern;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private String model;
    private String color;

    @Pattern(regexp = "^[a-zA-Z]{3}\\d[A-Za-z0-9]\\d{2}$")
    private String plate;
}
