package com.uam.caronex.mapper;

import com.uam.caronex.dto.VehicleRequest;
import com.uam.caronex.dto.VehicleResponse;
import com.uam.caronex.entity.VehicleEntity;

public class VehicleMapper {
    private VehicleMapper() {}

    public static VehicleEntity toEntity(VehicleRequest request) {
        return VehicleEntity.builder()
                .id(request.getId())
                .model(request.getModel())
                .color(request.getColor())
                .plate(request.getPlate())
                .places(request.getPlaces())
                .build();
    }

    public static VehicleEntity toEntity(String id, VehicleRequest request) {
        return VehicleEntity.builder()
                .id(id)
                .model(request.getModel())
                .color(request.getColor())
                .plate(request.getPlate())
                .places(request.getPlaces())
                .build();
    }

    public static VehicleResponse toResponse(VehicleEntity entity) {
        return VehicleResponse.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .color(entity.getColor())
                .plate(entity.getPlate())
                .places(entity.getPlaces())
                .build();
    }
}
