package com.ou.repositories.impl;

import com.ou.pojo.Floor;
import com.ou.repositories.FloorRepository;
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
public class FloorRepositoryImpl implements FloorRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public List<Floor> getFloor() {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Query q = session.createNamedQuery("Floor.findAll");
        return q.getResultList();
    }
}
