package com.ou.repositories;

import com.ou.dto.response.CabinetResponse;
import com.ou.pojo.Cabinet;
import com.ou.pojo.Item;
import com.ou.pojo.Room;

import java.util.List;
import java.util.Map;

public interface CabinetRepository {
    void createCabinet(Cabinet cabinet);
    List<Cabinet> getAllCabinet(Map<String, String> params);
    List<Item> getItemsByCabinetId(int cabinetId,Map<String, String> params);
}
