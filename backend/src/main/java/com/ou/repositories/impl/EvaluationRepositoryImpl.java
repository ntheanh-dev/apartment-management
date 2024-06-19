package com.ou.repositories.impl;

import com.ou.pojo.Evaluation;
import com.ou.pojo.Report;
import com.ou.repositories.EvaluationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
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
        Query q = session.createNamedQuery("Evaluation.findAll");
        return q.getResultList();
    }

    @Override
    public Long countAllEvaluations() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rD = q.from(Evaluation.class);

        q.multiselect(b.count(rD.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(b.function("MONTH", Integer.class, rD.get("createdDate")), LocalDate.now().getMonthValue()));

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        return (Long) query.getSingleResult();
    }
}
