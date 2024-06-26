package com.ou.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.dto.request.ItemCreationRequest;
import com.ou.dto.request.SetReceivedDateItem;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.LinkResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.mapper.ItemMapper;
import com.ou.pojo.Item;
import com.ou.repositories.ItemRepository;
import com.ou.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@PropertySource("classpath:application-dev.properties")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private Environment env;
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
        request.setDeliveryDate(LocalDate.now());
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

    @Override
    public PaginationResponse<ItemResponse> getItemsInMyRoom(Map<String,String> params) {
        var response = this.itemRepository.getItemsInMyRoom(params);
        String currentUrl = params.get("currentUrl");
        System.out.println(currentUrl);
        if(currentUrl != null && !currentUrl.isEmpty()){
            String nextLink = "";
            String prevLink = "";

            Long totalItemsCount = response.getCount();

            //------------ Parse Page------------
            String pageNumber = params.get("page");
            pageNumber = (pageNumber == null || pageNumber.isEmpty()) ? "1" : pageNumber;
            int page = Integer.parseInt(pageNumber);
            //-------------Parse size------------
            String size = params.get("size");
            size = (size == null || size.isEmpty()) ? env.getProperty("app.pageSize") : size;
            int pageSize = Integer.parseInt(size);
            //------------------------------------
            int nextPage = page + 1;
            Long toalPages = (totalItemsCount + pageSize - 1) / pageSize;

            if(nextPage > toalPages) {
                nextLink = null;
            } else {
                nextLink = String.format("%s?page=%d&size=%d",currentUrl, nextPage,pageSize);
            }

            if(page == 1 && page <= totalItemsCount) {
                prevLink = null;
            } else {
                int previousPage = page-1;
                prevLink = String.format("%s?page=%d&size=%d",currentUrl,previousPage,pageSize);
            }
            //---------------Parse Sort------------------
            String sortBy = params.get("sortBy");
            if (sortBy != null && !sortBy.isEmpty()) {
                String order = params.get("order");
                order = (order == null || order.isEmpty()) ? "asc" : order;

                String sortParam = String.format("&sortBy=%s&order=%s",sortBy,order);
                if(nextLink != null) {
                    nextLink += sortParam;
                }
                if(prevLink != null) {
                    prevLink += sortParam;
                }

            }
            response.setLinks(
                    LinkResponse.builder()
                            .next(nextLink)
                            .prev(prevLink)
                            .build()
            );
        }

        return response;
    }

    @Override
    public void setReceivedDateItems(SetReceivedDateItem itemIds) {
        this.itemRepository.setReceivedDateItems(itemIds);
    }
}
