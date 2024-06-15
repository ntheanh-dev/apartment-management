package com.ou.repositories.impl;

import com.ou.pojo.Contract;
import com.ou.pojo.Room;
import com.ou.repositories.ContractRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public class ContractReposityImpl implements ContractRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addContract(Contract contract) {
        Session s = this.factory.getObject().getCurrentSession();
        if(contract.getId()!=null) s.update(contract);
        else s.save(contract);
    }

    @Override
    public Contract findContractByRoomID(Room roomId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query query = s.getNamedQuery("Contract.findByRoom_IdOrderByCreatedDateDesc");
        query.setParameter("id", roomId.getId());

        return (Contract) query.getResultList().get(0);
    }

    @Override
    public List<Contract> getAllContractsActive() {
        Session s = this.factory.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Contract.findByEndedDateLessThan");
        query.setParameter("endedDate", LocalDate.now());
        return (List<Contract>) query.getResultList();
    }
}
