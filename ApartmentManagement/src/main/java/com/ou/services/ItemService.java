package com.ou.services;

import com.ou.dto.request.ItemCreationRequest;
import com.ou.dto.request.PaginationRequest;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.pojo.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    void addOrUpdateItem(Item item);
    void addItem(ItemCreationRequest itemCreationRequest);
    PaginationResponse<ItemResponse> getItemsInMyRoom(Map<String,String> params);
}
