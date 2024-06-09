package com.ou.repositories.impl;

import com.ou.pojo.Item;
import com.ou.repositories.ItemRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private LocalSessionFactoryBean factory;


    @Override
    public void addOrUpdateItem(Item item) {
        Session s = this.factory.getObject().getCurrentSession();
        if(item.getId() != null) {
            s.update(item);
        } else {
            s.save(item);
        }
    }
}
