package com.ou.services.impl;

import com.ou.dto.response.CabinetResponse;
import com.ou.dto.response.ItemResponse;
import com.ou.mapper.CabinetMapper;
import com.ou.repositories.CabinetRepository;
import com.ou.services.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CabinetServiceImpl implements CabinetService {

    @Autowired
    private CabinetRepository cabinetRepository;

    @Autowired
    private CabinetMapper cabinetMapper;

    @Override
    public List<CabinetResponse> getAllCabinet(Map<String, String> params) {
        var response = cabinetRepository.getAllCabinet(params);
        return cabinetMapper.toCabinetResponseList(response);
    }

    @Override
    public List<ItemResponse> getItemsByCabinetId(int cabinetId, Map<String, String> params) {
        var response = cabinetRepository.getItemsByCabinetId(cabinetId, params);
        return cabinetMapper.toCabinetItemResponseList(response);
    }
}
