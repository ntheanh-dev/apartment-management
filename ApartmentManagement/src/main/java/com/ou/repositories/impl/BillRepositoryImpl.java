package com.ou.repositories.impl;

import com.ou.pojo.Receipt;
import com.ou.repositories.BillRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class BillRepositoryImpl implements BillRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createReceipt(Receipt receipt) {
        Session session = sessionFactory.getCurrentSession();
        session.save(receipt);
    }


    @Override
    public List<Receipt> getAllReceipts() {
        return List.of();
    }
}
