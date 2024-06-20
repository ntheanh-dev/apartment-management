package com.ou.repositories;

import com.ou.pojo.Criterion;

import java.util.List;

public interface CriterionRepository {
    List<Criterion> findAll();
    Criterion findById(int id);
    void create(Criterion criterion);
}
