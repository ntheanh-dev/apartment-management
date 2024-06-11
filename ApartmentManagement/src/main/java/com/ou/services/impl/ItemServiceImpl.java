package com.ou.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.dto.request.ItemCreationRequest;
import com.ou.mapper.ItemMapper;
import com.ou.pojo.Item;
import com.ou.repositories.ItemRepository;
import com.ou.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public void addOrUpdateItem(Item item) {
        this.itemRepository.addOrUpdateItem(item);
    }

    @Override
    public void addItem(ItemCreationRequest itemCreationRequest) {
        var request = itemMapper.toItem(itemCreationRequest);
        if (!itemCreationRequest.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(itemCreationRequest.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                request.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                request.setImage("");
                Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        this.itemRepository.addItem(request);
    }
}
