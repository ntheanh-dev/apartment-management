package com.ou.services.impl;

import com.ou.pojo.Resident;
import com.ou.repositories.ResidentRepository;
import com.ou.services.ResidentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentServicesImpl implements ResidentServices {
    @Autowired
    private ResidentRepository residentRepository;
    @Override
    public List<Resident> getAllResidents() {
        return this.residentRepository.getAllResidents();
    }
}
