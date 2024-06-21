package com.ou.repositories.impl;

import com.ou.pojo.Contract;
import com.ou.pojo.Receipt;
import com.ou.pojo.Resident;
import com.ou.pojo.Room;
import com.ou.repositories.BillRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    public List<Receipt> getAllReceipts(Map<String, String> params) {
        Session s = this.sessionFactory.getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Receipt> q = b.createQuery(Receipt.class);
        Root r = q.from(Receipt.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("title"), String.format("%%%s%%", kw)));
        }
        String status = params.get("status");
        if (status != null && !status.equals("all")) {
            predicates.add(b.like(r.get("status"), String.format("%%%s%%", status)));
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        List<Receipt> receipts = query.getResultList();

        return receipts;
    }

    @Override
    public void updateBillById(int orderID,Long price) {
        Session s = this.sessionFactory.getCurrentSession();
        Receipt receipt = s.get(Receipt.class, orderID);
        receipt.setPrice(new BigDecimal(price));
        receipt.setStatus("Đã thu");
        s.update(receipt);
    }

    @Override
    public List<Receipt> getAllReceiptsByResidentID(int residentID) {
        Session s = this.sessionFactory.getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Receipt> q = b.createQuery(Receipt.class);
        Root receipt = q.from(Receipt.class);
        q.select(receipt);

        Join<Receipt, Contract> ContractJoin = receipt.join("contract", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(ContractJoin.get("residentUser"), residentID));
        predicates.add(b.like(receipt.get("status"), String.format("%%%s%%", "Chưa thu")));
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        List<Receipt> receipts = query.getResultList();

        return receipts;
    }
}
