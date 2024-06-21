package com.ou.controllers;


import com.ou.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params) {
        model.addAttribute("bill",billService.getAllBill(params));
        return "IndexBill";
    }
}
