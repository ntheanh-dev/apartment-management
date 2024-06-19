package com.ou.repositories.impl;

import com.ou.pojo.DetailEvoluation;
import com.ou.repositories.DetailEvaluationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DetailEvaluationRepositoryImpl implements DetailEvaluationRepository {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addDetailEvaluation(DetailEvoluation detailEvoluation) {
        Session s = sessionFactory.getCurrentSession();
        s.save(detailEvoluation);
    }
}
