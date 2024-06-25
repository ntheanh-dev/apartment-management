package com.ou.controllers;

import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        try{
            this.userService.deleteUser(userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/room/";
    }

}
