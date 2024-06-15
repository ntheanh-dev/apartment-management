package com.ou.repositories.impl;

import com.ou.pojo.ReceiptDetail;
import com.ou.repositories.BillDetailRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BillDetailRepositoryImpl implements BillDetailRepository {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void createBillDetail(ReceiptDetail receiptDetail) {
        Session s = sessionFactory.getCurrentSession();
        s.save(receiptDetail);
    }

    @Override
    public List<ReceiptDetail> getReceiptDetailByBillId(int billId) {
        return List.of();
    }
}
