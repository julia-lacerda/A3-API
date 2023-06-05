package com.uam.caronex.service;

import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.mapper.RideMapper;
import com.uam.caronex.dto.RideRequest;
import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.repository.RideRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RideService {

    private RideRepository rideRepository;

    private RideMapper rideMapper;

    @Autowired
    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }
    public List<RideResponse> getRides(RideRequest rideRequest) {
        List<RideEntity> list = rideRepository.getRides(RideMapper.toModel(rideRequest));
        return list.stream().map(RideMapper::toResponse).toList();
    }


}
