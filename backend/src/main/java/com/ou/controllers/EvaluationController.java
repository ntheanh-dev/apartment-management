package com.ou.controllers;


import com.ou.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("evaluation",this.evaluationService.getAllEvaluations());
        return "IndexEvaluation";
    }
}
