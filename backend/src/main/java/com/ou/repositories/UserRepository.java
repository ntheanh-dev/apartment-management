package com.ou.repositories;

import com.ou.dto.request.ChangePasswordRequest;
import com.ou.pojo.User;

public interface UserRepository {
    User getUserByUsername(String username);
    User addUser(User user);
    User authUser(String username, String password);
    boolean userExistsByUsername(String username);
    void changePassword(User user);
    void deleteUser(int id);
}
