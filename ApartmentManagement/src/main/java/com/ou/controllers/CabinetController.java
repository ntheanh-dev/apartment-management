package com.ou.controllers;

import com.ou.pojo.Cabinet;
import com.ou.services.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private CabinetService cabinetService;


    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params) {
        var cabinets = cabinetService.getAllCabinet(params);
        model.addAttribute("cabinets", cabinets);
        return "cabinetIndex";
    }

    @GetMapping("/{cabinetId}/detail")
    public String cabinetView(Model model, @PathVariable Integer cabinetId,@RequestParam Map<String, String> params) {
        var cabinetItems = cabinetService.getItemsByCabinetId(cabinetId,params);
        model.addAttribute("cabinetItems", cabinetItems);
        System.out.println(cabinetItems.size());
        return "cabinetDetail";
    }
}
