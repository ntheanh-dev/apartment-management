package com.ou.repositories.impl;

import com.ou.pojo.Cabinet;
import com.ou.repositories.CabinetRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CabinetRepositoryImpl implements CabinetRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public void createCabinet(Cabinet cabinet) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        if(cabinet.getId() != null){
            session.update(cabinet);
        }else{
            session.save(cabinet);
        }
    }
}
