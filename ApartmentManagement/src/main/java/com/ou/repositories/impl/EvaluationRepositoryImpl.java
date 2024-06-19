package com.ou.repositories.impl;

import com.ou.pojo.Evaluation;
import com.ou.repositories.EvaluationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class EvaluationRepositoryImpl implements EvaluationRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public Evaluation addEvaluation(Evaluation evaluation) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.save(evaluation);
        return evaluation;
    }

    @Override
    public List<Evaluation> getAllEvaluations() {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Query q =session.createNamedQuery("Evaluation.findAll");
        return q.getResultList();
    }
}
