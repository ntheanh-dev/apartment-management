package com.ou.repositories.impl;

import com.ou.pojo.Report;
import com.ou.pojo.Room;
import com.ou.repositories.ReportRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Report addReport(Report report) {
        Session session = sessionFactory.getCurrentSession();
        session.save(report);
        return report;
    }

    @Override
    public List<Report> getAllReports(Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Report> q = b.createQuery(Report.class);
        Root r = q.from(Report.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<Predicate>();
        String status = params.get("status");
        if (status != null && !status.equals("all")) {
            predicates.add(b.like(r.get("status"), String.format("%%%s%%", status)));
        }
        String startDate = params.get("kw");
        if (startDate != null && !startDate.isEmpty()) {
            LocalDate dateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            predicates.add(b.equal(r.get("createdDate"), dateTime));
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        return query.getResultList();
    }
}
