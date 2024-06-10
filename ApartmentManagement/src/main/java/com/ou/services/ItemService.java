package com.ou.services;

import com.ou.dto.request.ItemCreationRequest;
import com.ou.pojo.Item;

public interface ItemService {
    void addOrUpdateItem(Item item);
    void addItem(ItemCreationRequest itemCreationRequest);
}
