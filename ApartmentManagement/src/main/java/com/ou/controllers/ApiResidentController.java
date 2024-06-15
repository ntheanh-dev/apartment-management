package com.ou.controllers;

import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.ResidentResponse;
import com.ou.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/residents/")
public class ApiResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping(path = "my-info/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ResidentResponse> getMyInfo() {

        return ApiResponse.<ResidentResponse>builder()
                .result(residentService.getResident())
                .build();
    }
}
