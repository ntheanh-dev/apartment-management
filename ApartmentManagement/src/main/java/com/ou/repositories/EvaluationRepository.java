package com.ou.repositories;

import com.ou.pojo.Evaluation;

import java.util.List;

public interface EvaluationRepository {
    Evaluation addEvaluation(Evaluation evaluation);
    List<Evaluation> getAllEvaluations();
    Long countAllEvaluations();
    Boolean canEvaluate(int id);
}
