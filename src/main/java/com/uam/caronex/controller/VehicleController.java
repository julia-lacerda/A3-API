package com.uam.caronex.controller;

import com.uam.caronex.dto.VehicleRequest;
import com.uam.caronex.dto.VehicleResponse;
import com.uam.caronex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class VehicleController {
    private final UserService userService;

    @PostMapping("/{cpf}/vehicles")
    public VehicleResponse createVehicle(@PathVariable String cpf, @RequestBody VehicleRequest request) {
        return userService.addVehicle(cpf, request);
    }

    @PutMapping("/{cpf}/vehicles/{id}")
    public VehicleResponse updateVehicle(
            @PathVariable(value = "cpf") String cpf,
            @PathVariable(value = "id") String vehicleId,
            @RequestBody VehicleRequest request) {
        return userService.updateVehicle(cpf, vehicleId, request);
    }

    @DeleteMapping("/{cpf}/vehicles/{id}")
    public void deleteVehicle(@PathVariable String cpf, @PathVariable String id) {
        userService.deleteVehicle(cpf, id);
    }
}
