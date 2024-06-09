package com.ou.services.impl;

import com.ou.pojo.Item;
import com.ou.repositories.ItemRepository;
import com.ou.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void addOrUpdateItem(Item item) {
        this.itemRepository.addOrUpdateItem(item);
    }
}
