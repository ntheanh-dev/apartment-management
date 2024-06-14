package com.ou.controllers;

import com.ou.dto.request.PaginationRequest;
import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.LinkResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.pojo.Item;
import com.ou.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/items")
@PropertySource("classpath:app.properties")
public class ApiItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private Environment env;

    @PostMapping(path = "/")
    public ApiResponse<PaginationResponse<ItemResponse>>  getItems(
            @RequestBody(required = false) PaginationRequest paginationRequest,
            HttpServletRequest request
    ) {
        var response = itemService.getItemsInMyRoom(paginationRequest);
        String currentUrl = request.getRequestURL().toString();

        String nextLink = "";
        String prevLink = "";

        Long totalItemsCount = response.getCount();
        int page = paginationRequest.getPage();
        int nextPage = page + 1;
        int pageSize = paginationRequest.getSize() == 0 ? Integer.parseInt(env.getProperty("app.pageSize").toString())
                : paginationRequest.getSize();
        Long toalPages = (totalItemsCount + pageSize - 1) / pageSize;

        if(nextPage > toalPages) {
            nextLink = null;
        } else {
            nextLink = String.format("%s?page=%d&size=%d",currentUrl, nextPage,pageSize);
        }

        if(page == 1 && page <= totalItemsCount) {
            prevLink = null;
        } else {
            int previousPage = paginationRequest.getPage()-1;
            prevLink = String.format("%s?page=%d&size=%d",currentUrl,previousPage,pageSize);
        }

        if(paginationRequest.getSortBy() != null) {
            String sortBy = paginationRequest.getSortBy();
            String order = paginationRequest.getOrder().toLowerCase();

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

        return ApiResponse.<PaginationResponse<ItemResponse>>builder()
                .message("success")
                .result(response)
                .build();
    }
}
