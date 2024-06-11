package com.ou.services.impl;

import com.ou.dto.response.CabinetResponse;
import com.ou.dto.response.ItemResponse;
import com.ou.mapper.CabinetMapper;
import com.ou.repositories.CabinetRepository;
import com.ou.services.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CabinetServiceImpl implements CabinetService {

    @Autowired
    private CabinetRepository cabinetRepository;

    @Autowired
    private CabinetMapper cabinetMapper;

    @Override
    public List<CabinetResponse> getAllCabinet(Map<String, String> params) {
        var response = cabinetRepository.getAllCabinet(params);


        return response.stream().map(s -> CabinetResponse.builder()
                .id(Integer.parseInt(s[0].toString()))
                .isActive(Boolean.parseBoolean(s[1].toString()))
                .contractId(Integer.parseInt(s[2].toString()))
                .unreceivedItemCount(Integer.parseInt(s[3].toString()) - 1)
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<ItemResponse> getItemsByCabinetId(int cabinetId, Map<String, String> params) {
        var response = cabinetRepository.getItemsByCabinetId(cabinetId, params);
        return cabinetMapper.toCabinetItemResponseList(response);
    }

    @Override
    public Boolean isActiveCabinet(int cabinetId) {
        return this.cabinetRepository.isActiveCabinet(cabinetId);
    }

    @Override
    public void closeExpiredContractCabinets() {
        this.cabinetRepository.closeExpiredContractCabinets();
    }

}
