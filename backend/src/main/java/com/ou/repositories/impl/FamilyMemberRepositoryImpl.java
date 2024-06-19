package com.ou.repositories.impl;

import com.ou.dto.request.RemoveFamilymemberRequest;
import com.ou.exception.AppException;
import com.ou.exception.ErrorCode;
import com.ou.pojo.FamilyMember;
import com.ou.pojo.Resident;
import com.ou.pojo.User;
import com.ou.repositories.FamilyMemberRepository;
import com.ou.repositories.ResidentRepository;
import com.ou.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FamilyMemberRepositoryImpl implements FamilyMemberRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public List<FamilyMember> findAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = this.userRepository.getUserByUsername(username);

        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery<FamilyMember> criteria = builder.createQuery(FamilyMember.class);
        Root<FamilyMember> root = criteria.from(FamilyMember.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("residentUser"), u.getId()));
        predicates.add(builder.equal(root.get("active"),true));


        criteria.select(root);

        Query query = s.createQuery(criteria);


        return (List<FamilyMember>) query.getResultList();
    }

    @Override
    public void removeRelativesById(RemoveFamilymemberRequest ids) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<FamilyMember> cq = builder.createQuery(FamilyMember.class);
        Root<FamilyMember> familyMemberRoot = cq.from(FamilyMember.class);

        cq.select(familyMemberRoot).where(familyMemberRoot.get("id").in(ids.getId()));
        List<FamilyMember> items = s.createQuery(cq).getResultList();

        items.forEach(s::delete);
    }

    @Override
    public FamilyMember add(FamilyMember familyMember) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = this.userRepository.getUserByUsername(username);

        Session s = this.factory.getObject().getCurrentSession();

        Resident r = this.residentRepository.getResidentById(u.getId());
        if(r == null) {
            throw new AppException(ErrorCode.RESIDENT_NOT_FOUND);
        }

        familyMember.setResidentUser(r);
        familyMember.setCreatedAt(LocalDate.now());
        familyMember.setActive(true);
        s.save(familyMember);
        return familyMember;
    }
}
