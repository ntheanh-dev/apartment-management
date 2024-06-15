package com.ou.controllers;

import com.ou.dto.request.SetReceivedDateItem;
import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/items")
@CrossOrigin
public class ApiItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<PaginationResponse<ItemResponse>>  getItems(
            @RequestParam Map<String, String> params,
            HttpServletRequest request
    ) {
        //-------- Dùng để build next and prev link
        String currentUrl = request.getRequestURL().toString();
        params.put("currentUrl", currentUrl);

        var response = itemService.getItemsInMyRoom(params);
        return ApiResponse.<PaginationResponse<ItemResponse>>builder()
                .message("success")
                .result(response)
                .build();
    }

    @PostMapping(path = "/",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
    },produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Void> setReceivedDateItems(
            @RequestBody SetReceivedDateItem itemId
    ) {
        itemService.setReceivedDateItems(itemId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
