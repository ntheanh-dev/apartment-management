package com.ou.services;

import com.ou.dto.response.CabinetResponse;
import com.ou.dto.response.ItemResponse;

import java.util.List;
import java.util.Map;

public interface CabinetService {
    List<CabinetResponse> getAllCabinet(Map<String, String> params);
    List<ItemResponse> getItemsByCabinetId(int cabinetId, Map<String, String> params);
    Boolean isActiveCabinet(int cabinetId);
}
