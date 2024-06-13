package com.ou.repositories;

import com.ou.dto.request.PaginationRequest;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.pojo.Item;

import java.util.List;

public interface ItemRepository {
    void addOrUpdateItem(Item item);
    void addItem(Item item);
    PaginationResponse<ItemResponse> getItemsInMyRoom(PaginationRequest paginationRequest);


}
