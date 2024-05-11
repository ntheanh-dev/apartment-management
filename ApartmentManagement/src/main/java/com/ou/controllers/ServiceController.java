package com.ou.controllers;

import com.ou.dto.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @GetMapping("/")
    public String index() {
        return "serviceIndex";
    }

    @GetMapping("/create")
    public String creatService() {
        return "serviceCreation";
    }

    @GetMapping("{serviceId}/edit")
    public String editService(@PathVariable int serviceId, Model model) {
        // Get servicebyid here then modify it

        Service service = Service.builder()
                .name("Nuoc")
                .price(1000)
                .note("Adbc")
                .build();
        model.addAttribute("service", service);
        return "serviceEdit";
    }


}
