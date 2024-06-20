package com.ou.services.impl;

import com.ou.dto.request.DetailEvaluationFrom;
import com.ou.dto.request.FormEvaluation;
import com.ou.pojo.DetailEvoluation;
import com.ou.pojo.Evaluation;
import com.ou.pojo.Resident;
import com.ou.pojo.User;
import com.ou.repositories.*;
import com.ou.services.EvaluationService;
import com.ou.services.ResidentService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private DetailEvaluationRepository evaluationDetailRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private CriterionRepository criterionRepository;
    @Override
    public Evaluation addEvaluation(FormEvaluation formEvaluation) {
        Evaluation evaluation = formEvaluation.getEvaluation();
        User u = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Resident r = residentRepository.getResidentById(u.getId());
        evaluation.setResidentUser(r);
        evaluation.setCreatedDate(LocalDate.now());
        evaluationRepository.addEvaluation(evaluation);
        for(DetailEvaluationFrom df : formEvaluation.getDetailEvaluations()) {
            DetailEvoluation d = new DetailEvoluation();
            d.setEvaluation(evaluation);
            d.setRate(df.getRate());
            d.setCriterion(criterionRepository.findById(df.getIdCriterion()));
            evaluationDetailRepository.addDetailEvaluation(d);
        }
        return evaluation;
    }

    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.getAllEvaluations();
    }

    @Override
    public Long countAllEvaluations() {
        return evaluationRepository.countAllEvaluations();
    }

    @Override
    public Boolean canEvaluation(int id) {
        return evaluationRepository.canEvaluate(id);
    }
}
