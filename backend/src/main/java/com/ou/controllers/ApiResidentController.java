package com.ou.controllers;

import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.ResidentResponse;
import com.ou.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(path = "/change-avatar/",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    },produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<String > changeAvatar(@RequestPart MultipartFile[] file) {
        String url = residentService.changeAvatar(file);
        return ApiResponse.<String>builder()
                .result(url)
                .build();
    }
}
