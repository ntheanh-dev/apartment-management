package com.ou.repositories.impl;

import com.ou.pojo.Criterion;
import com.ou.pojo.Room;
import com.ou.repositories.CriterionRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CriterionRepositoryImpl implements CriterionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public List<Criterion> findAll() {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query query = session.createNamedQuery("Criterion.findAll");
        return query.getResultList();
    }

    @Override
    public Criterion findById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(Criterion.class, id);
    }

    @Override
    public void create(Criterion criterion) {
        Session session = sessionFactory.getObject().getCurrentSession();
        if(criterion.getId() != null && criterion.getId() > 0){
            session.update(criterion);
        }else{
            session.save(criterion);
        }
    }
}
