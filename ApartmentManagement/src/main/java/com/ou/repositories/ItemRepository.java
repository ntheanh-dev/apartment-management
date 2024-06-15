package com.ou.repositories;

import com.ou.dto.request.PaginationRequest;
import com.ou.dto.request.SetReceivedDateItem;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.pojo.Item;

import java.util.List;
import java.util.Map;

public interface ItemRepository {
    void addOrUpdateItem(Item item);
    void addItem(Item item);
    PaginationResponse<ItemResponse> getItemsInMyRoom(Map<String,String> params);
    void setReceivedDateItems(SetReceivedDateItem itemIds);

}
