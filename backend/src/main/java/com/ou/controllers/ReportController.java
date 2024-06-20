package com.ou.controllers;

import com.ou.pojo.Report;
import com.ou.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params){
        model.addAttribute("reports", reportService.getAllReport(params));
        return "IndexReport";
    }
}
