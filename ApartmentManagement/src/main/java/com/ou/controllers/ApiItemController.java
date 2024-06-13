package com.ou.controllers;

import com.ou.dto.request.PaginationRequest;
import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.pojo.Item;
import com.ou.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/items")
public class ApiItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/")
    public ApiResponse<PaginationResponse<ItemResponse>>  getItems(
            @RequestBody(required = false) PaginationRequest paginationRequest
    ) {
        var response = itemService.getItemsInMyRoom(paginationRequest);
        return ApiResponse.<PaginationResponse<ItemResponse>>builder()
                .message("success")
                .result(response)
                .build();
    }
}
