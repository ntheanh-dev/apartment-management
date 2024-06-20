package com.ou.services;

import com.ou.dto.request.FormEvaluation;
import com.ou.pojo.Evaluation;

import java.util.List;

public interface EvaluationService {
    Evaluation addEvaluation(FormEvaluation formEvaluation);
    List<Evaluation> getAllEvaluations();
    Long countAllEvaluations();
    Boolean canEvaluation(int id);
}
