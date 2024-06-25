package com.ou.controllers;

import com.ou.pojo.Report;
import com.ou.pojo.User;
import com.ou.services.ReportService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiReportController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @PostMapping("/report/")
    public ResponseEntity<Object> addEvaluation(@RequestBody Report report) throws IOException, InterruptedException {
        ResponseEntity<Object> response = new ResponseEntity<>("báo cáo thành công", HttpStatus.OK);
        User u = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        this.reportService.addReport(report,u);
        return response;
    }
}
