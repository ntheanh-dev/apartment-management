package com.ou.services;

import com.ou.dto.request.UserCreationRequest;
import com.ou.dto.response.UserResponse;
import com.ou.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    void addUser(UserCreationRequest user);
    boolean authUser(String username, String password);
    UserResponse getMyInfo();
}
