package com.ou.controllers;

import com.ou.dto.request.CreateUserRequest;
import com.ou.services.JwtService;
import com.ou.dto.request.AuthenticationRequest;
import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.AuthenticationResponse;
import com.ou.pojo.User;
import com.ou.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
@Slf4j
public class ApiUserController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/token",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var response = jwtService.authenticated(authenticationRequest);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping(path = "/users/", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateUserRequest createUserRequest) {
        User u = new User();
        u.setUsername(createUserRequest.getUsername());
        u.setPassword(this.passwordEncoder.encode(createUserRequest.getPassword()));
        u.setRole("ROLE_USER");
        u.setActive(true);

        this.userService.addUser(u);
    }
}
