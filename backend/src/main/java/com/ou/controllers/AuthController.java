package com.ou.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(@RequestParam Map<String,String> params) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!username.equals("anonymousUser")) {
            return "redirect:/";
        }
        return "login";
    }
}
