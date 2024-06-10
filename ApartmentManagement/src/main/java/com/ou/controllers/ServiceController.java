package com.ou.controllers;

import com.ou.dto.Service;
import com.ou.services.ServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceServices serviceServices;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("services", this.serviceServices.getServices());
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
