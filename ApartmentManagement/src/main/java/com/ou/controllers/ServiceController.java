package com.ou.controllers;

import com.ou.pojo.Service;
import com.ou.services.ServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String creatService( Model model) {
        model.addAttribute("service", new Service());
        return "serviceCreation";
    }

    @GetMapping("{serviceId}/edit")
    public String editService(@PathVariable int serviceId, Model model) {
        // Get servicebyid here then modify it
        model.addAttribute("service", this.serviceServices.getServiceById(serviceId));
        return "serviceCreation";
    }
    @PostMapping("/updateOrCreate")
    public String updateService(Model model, @ModelAttribute("service") Service service, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                serviceServices.addService(service);
                return "redirect:./";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("errMsg", ex.toString());
            }
        }
        return "serviceIndex";
    }


}
