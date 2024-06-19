package com.ou.services.impl;

import com.ou.pojo.Criterion;
import com.ou.repositories.CriterionRepository;
import com.ou.services.CriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriterionServiceImpl implements CriterionService {
    @Autowired
    private CriterionRepository criterionRepository;
    @Override
    public List<Criterion> getCriterions() {
        return criterionRepository.findAll();
    }
}
