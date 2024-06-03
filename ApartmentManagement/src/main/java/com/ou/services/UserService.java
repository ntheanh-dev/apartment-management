package com.ou.services;

import com.ou.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    void addUser(User user);
    boolean authUser(String username, String password);
}
