package com.ou.controllers;

import com.ou.dto.request.ChangePasswordRequest;
import com.ou.dto.request.UserCreationRequest;
import com.ou.dto.response.UserResponse;
import com.ou.services.JwtService;
import com.ou.dto.request.AuthenticationRequest;
import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.AuthenticationResponse;
import com.ou.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api")
@Slf4j
@CrossOrigin
public class ApiUserController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/token",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var response = jwtService.authenticated(authenticationRequest);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping(path = "/users/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserCreationRequest userCreationRequest) {
        UserResponse u = userService.addUser(userCreationRequest);
    }

    @PostMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PostMapping(path = "/change-password/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<Void> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        this.userService.changePassword(changePasswordRequest);
        return ApiResponse.<Void>builder().build();
    }

}
