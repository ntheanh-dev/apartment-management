package com.ou.repositories.impl;

import com.ou.pojo.Receipt;
import com.ou.pojo.Report;
import com.ou.repositories.StatRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class StatRepositoryImpl implements StatRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Object[]> statsRevenueByMonth(int year) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rD = q.from(Receipt.class);

        q.multiselect(b.function("MONTH", Integer.class, rD.get("startedDate")),b.sum(rD.get("price")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(b.function("YEAR", Integer.class, rD.get("startedDate")), year));

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(b.function("MONTH", Integer.class, rD.get("startedDate")));

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> statReport() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rD = q.from(Report.class);

        q.multiselect(rD.get("status"),b.count(rD.get("id")));

        q.groupBy(rD.get("status"));
        Query query = s.createQuery(q);

        return query.getResultList();
    }
}
