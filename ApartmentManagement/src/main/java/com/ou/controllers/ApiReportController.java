package com.ou.controllers;

import com.ou.dto.request.FormEvaluation;
import com.ou.pojo.Report;
import com.ou.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiReportController {
    @Autowired
    private ReportService reportService;
    @PostMapping("/report/")
    public ResponseEntity<Object> addEvaluation(@RequestBody Report report) throws IOException, InterruptedException {
        return new ResponseEntity<>(this.reportService.addReport(report), HttpStatus.OK);
    }
}
