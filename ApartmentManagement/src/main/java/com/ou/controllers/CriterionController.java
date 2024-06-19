package com.ou.controllers;


import com.ou.services.CriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/criterion")
public class CriterionController {
    @Autowired
    private CriterionService criterionService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("criterions",criterionService.getCriterions());
        return "IndexCriterion";
    }
}
