package com.ou.controllers;

import com.ou.dto.request.FormEvaluation;
import com.ou.pojo.Criterion;
import com.ou.pojo.Evaluation;
import com.ou.pojo.User;
import com.ou.services.CriterionService;
import com.ou.services.EvaluationService;
import com.ou.services.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ApiEvaluationController {
    @Autowired
    public CriterionService criterionService;

    @Autowired
    public UserService userService;

    @Autowired
    public EvaluationService evaluationService;

    @GetMapping("/evaluation/")
    public ResponseEntity<Object> criterion() {
        User u = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!evaluationService.canEvaluation(u.getId())){
            return new ResponseEntity<>("{\"detail\":\"Error!!!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.criterionService.getCriterions(), HttpStatus.OK);
    }
    @PostMapping("/evaluation/")
    public ResponseEntity<Object> addEvaluation(@RequestBody FormEvaluation formEvaluation) {
        return new ResponseEntity<>(this.evaluationService.addEvaluation(formEvaluation), HttpStatus.OK);
    }
}
