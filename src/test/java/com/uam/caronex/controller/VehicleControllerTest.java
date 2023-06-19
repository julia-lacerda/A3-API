package com.uam.caronex.controller;

import com.uam.caronex.dto.VehicleRequest;
import com.uam.caronex.dto.VehicleResponse;
import com.uam.caronex.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private VehicleController vehicleController;

    @Test
    public void shouldCreateVehicleOnDatabase_WhenCreateVehicle() {
        String cpf = "123456789";
        VehicleRequest request = new VehicleRequest();
        VehicleResponse expectedResponse = new VehicleResponse();

        when(userService.addVehicle(eq(cpf), eq(request))).thenReturn(expectedResponse);

        VehicleResponse actualResponse = vehicleController.createVehicle(cpf, request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldUpdateVehicleOnDatabase_WhenUpdateVehicle() {
        String cpf = "123456789";
        String vehicleId = "vehicle123";
        VehicleRequest request = new VehicleRequest();
        VehicleResponse expectedResponse = new VehicleResponse();

        when(userService.updateVehicle(eq(cpf), eq(vehicleId), eq(request))).thenReturn(expectedResponse);

        VehicleResponse actualResponse = vehicleController.updateVehicle(cpf, vehicleId, request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldDeleteVehicleOnDatabase_WhenDeleteVehicle() {
        String cpf = "123456789";
        String vehicleId = "vehicle123";

        vehicleController.deleteVehicle(cpf, vehicleId);

        verify(userService, times(1)).deleteVehicle(eq(cpf), eq(vehicleId));
    }

}
