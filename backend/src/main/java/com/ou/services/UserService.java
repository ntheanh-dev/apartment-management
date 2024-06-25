package com.ou.services;

import com.ou.dto.RoomRegisterDto;
import com.ou.dto.request.ChangePasswordRequest;
import com.ou.dto.request.UserCreationRequest;
import com.ou.dto.response.UserResponse;
import com.ou.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    UserResponse addUser(UserCreationRequest user);
    User authUser(String username, String password);
    UserResponse getMyInfo();
    void createContract(RoomRegisterDto user,Integer roomId);
    void changePassword(ChangePasswordRequest changePasswordRequest);
    User addAdmin(User user);
    boolean userExistsByUsername(String username);
    void deleteUser(int id);
}
