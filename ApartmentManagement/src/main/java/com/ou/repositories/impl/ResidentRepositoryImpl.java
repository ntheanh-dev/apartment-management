package com.ou.repositories.impl;

import com.ou.pojo.Resident;
import com.ou.repositories.ResidentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class ResidentRepositoryImpl implements ResidentRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addResident(Resident resident) {
        Session s = this.factory.getObject().getCurrentSession();
        if(checkExist(resident)){
            s.update(resident);
        }else{
            s.save(resident);
        }
    }

    @Override
    public List<Resident> getAllResidents() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Resident.findByUser_RoleAndUser_Active");
        return q.getResultList();
    }

    @Override
    public Resident getResidentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query query = s.createQuery("from Resident r where r.id = :id");
        query.setParameter("id", id);
        return (Resident) query.uniqueResult();
    }

    @Override
    public Resident getResident() {
        Session s = this.factory.getObject().getCurrentSession();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = this.userRepository.getUserByUsername(username);

        Resident r = this.getResidentById(u.getId());
        if(r == null) {
            throw new AppException(ErrorCode.RESIDENT_NOT_FOUND);
        }

        return r;
    }

    public Boolean checkExist(Resident resident) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Resident.existsByUser_Id");
        q.setParameter("id", resident.getUser().getId());
        return (Boolean) q.getSingleResult();
    }

}
