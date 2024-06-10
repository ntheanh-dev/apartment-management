package com.ou.repositories;

import com.ou.pojo.Item;

public interface ItemRepository {
    void addOrUpdateItem(Item item);
    void addItem(Item item);
}
