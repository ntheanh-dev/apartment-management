package com.ou.services.impl;

import com.ou.dto.response.ResidentResponse;
import com.ou.mapper.ResidentMapper;
import com.ou.pojo.Resident;
import com.ou.repositories.ResidentRepository;
import com.ou.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ResidentMapper residentMapper;

    @Override
    public ResidentResponse getResident() {
        var resident = residentRepository.getResident();
        return residentMapper.toResidentResponse(resident);
    }
}
