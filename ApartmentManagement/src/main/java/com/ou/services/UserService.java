package com.ou.services;

import com.ou.dto.RoomRegisterDto;
import com.ou.dto.request.UserCreationRequest;
import com.ou.dto.response.UserResponse;
import com.ou.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    UserResponse addUser(UserCreationRequest user);
    User authUser(String username, String password);
    UserResponse getMyInfo();
    void createContract(RoomRegisterDto user,Integer roomId);
}
