package com.uam.caronex.mapper;

import com.uam.caronex.dto.VehicleRequest;
import com.uam.caronex.dto.VehicleResponse;
import com.uam.caronex.entity.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VehicleMapperTest {

    @Test
    public void shouldMapVehicleRequestToVehicleEntity() {
        VehicleRequest vehicleRequest = new VehicleRequest();
        vehicleRequest.setModel("Ford Ka");
        vehicleRequest.setColor("Preto");
        vehicleRequest.setPlate("ABC1D23");
        vehicleRequest.setPlaces(4);

        VehicleEntity vehicleEntity = VehicleMapper.toEntity(vehicleRequest);

        assertEquals(vehicleRequest.getModel(), vehicleEntity.getModel());
        assertEquals(vehicleRequest.getColor(), vehicleEntity.getColor());
        assertEquals(vehicleRequest.getPlate(), vehicleEntity.getPlate());
        assertEquals(vehicleRequest.getPlaces(), vehicleEntity.getPlaces());
    }

    @Test
    void shouldMapVehicleRequestToVehicleEntityWithId() {
        String id = "123";
        VehicleRequest request = new VehicleRequest();
        request.setModel("Toyota Camry");
        request.setColor("Blue");
        request.setPlate("ABC123");
        request.setPlaces(4);

        VehicleEntity entity = VehicleMapper.toEntity(id, request);

        assertEquals(id, entity.getId());
        assertEquals("Toyota Camry", entity.getModel());
        assertEquals("Blue", entity.getColor());
        assertEquals("ABC123", entity.getPlate());
        assertEquals(4, entity.getPlaces());
    }


    @Test
    void shouldMapVehicleEntityToVehicleResponse() {
        // Given
        VehicleEntity entity = VehicleEntity.builder()
                .id("123")
                .model("Toyota Camry")
                .color("Blue")
                .plate("ABC123")
                .places(4)
                .build();

        // When
        VehicleResponse response = VehicleMapper.toResponse(entity);

        // Then
        assertEquals("123", response.getId());
        assertEquals("Toyota Camry", response.getModel());
        assertEquals("Blue", response.getColor());
        assertEquals("ABC123", response.getPlate());
        assertEquals(4, response.getPlaces());
    }
}


