package com.ou.services.impl;

import com.ou.pojo.Floor;
import com.ou.repositories.FloorRepository;
import com.ou.services.FloorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServicesImpl implements FloorServices {
    @Autowired
    private FloorRepository floorRepository;


    @Override
    public List<Floor> getFloor() {
        return this.floorRepository.getFloor();
    }
}
