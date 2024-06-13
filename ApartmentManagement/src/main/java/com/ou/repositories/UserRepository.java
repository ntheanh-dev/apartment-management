package com.ou.repositories;

import com.ou.pojo.User;

public interface UserRepository {
    User getUserByUsername(String username);
    User addUser(User user);
    User authUser(String username, String password);
    boolean userExistsByUsername(String username);
}
