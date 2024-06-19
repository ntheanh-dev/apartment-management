package com.ou.controllers;

import com.ou.dto.request.FormEvaluation;
import com.ou.pojo.Criterion;
import com.ou.pojo.Evaluation;
import com.ou.services.CriterionService;
import com.ou.services.EvaluationService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiEvaluationController {
    @Autowired
    public CriterionService criterionService;

    @Autowired
    public EvaluationService evaluationService;

    @GetMapping("/evaluation/")
    public ResponseEntity<List<Criterion>> criterion() {
        return new ResponseEntity<>(this.criterionService.getCriterions(), HttpStatus.OK);
    }
    @PostMapping("/evaluation/")
    public ResponseEntity<Object> addEvaluation(@RequestBody FormEvaluation formEvaluation) {
        System.out.println(formEvaluation.getEvaluation().getFeedback());
        return new ResponseEntity<>(this.evaluationService.addEvaluation(formEvaluation), HttpStatus.OK);
    }
}
