package com.ou.controllers;


import com.ou.pojo.User;
import com.ou.services.BillService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class ApiBillController {
    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;
    @GetMapping("/bill/")
    public ResponseEntity<Object> criterion() {
        User u = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity<>(this.billService.getAllReceiptsByResidentID(u.getId()), HttpStatus.OK);
    }
}
