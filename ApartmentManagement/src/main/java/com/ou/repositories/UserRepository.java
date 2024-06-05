package com.ou.repositories;

import com.ou.pojo.User;

public interface UserRepository {
    User getUserByUsername(String username);
    void addUser(User user);
    boolean authUser(String username, String password);
    boolean userExistsByUsername(String username);
}
