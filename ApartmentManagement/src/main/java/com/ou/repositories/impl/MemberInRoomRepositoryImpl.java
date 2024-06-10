package com.ou.repositories.impl;

import com.ou.pojo.Contract;
import com.ou.pojo.Resident;
import org.hibernate.Session;
import com.ou.pojo.MemberInRoom;
import com.ou.repositories.MemberInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class MemberInRoomRepositoryImpl implements MemberInRoomRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public void addMemberInRoom(MemberInRoom memberInRoom) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        if(memberInRoom.getId() != null){
            session.update(memberInRoom);
        }else{
            session.save(memberInRoom);
        }
    }

    @Override
    public List<MemberInRoom> getListOfMemberInRoom(Contract contract) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Query q = session.createNamedQuery("MemberInRoom.findByContract_Id");
        q.setParameter("id", contract.getId());
        return q.getResultList();
    }

    @Override
    public boolean checkExistence(Contract contract, Resident resident) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Query q = session.createNamedQuery("MemberInRoom.findByContract_IdAndResidentUser_Id");
        q.setParameter("id1", contract.getId());
        q.setParameter("id2", resident.getId());

        return q.getResultList().isEmpty();
    }
}
