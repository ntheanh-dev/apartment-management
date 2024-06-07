package com.ou.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {
    @GetMapping("/")
    public String index() {
        return "cabinetIndex";
    }

    @GetMapping("/{cabinetId}/detail")
    public String cabinetView(Model model, @PathVariable Integer cabinetId) {

        return "cabinetDetail";
    }
}
