package com.uam.caronex.model;

import com.uam.caronex.entity.UserEntity;
import com.uam.caronex.util.RideStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideModel {

    private String cpf;
    private Double[] origin;
    private Double[] destination;
    private LocalDateTime dateTime;
    private RideStatusEnum status;

}
