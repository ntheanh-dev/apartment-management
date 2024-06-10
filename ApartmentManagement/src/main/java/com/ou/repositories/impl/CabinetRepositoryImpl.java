package com.ou.repositories.impl;

import com.ou.pojo.Cabinet;
import com.ou.pojo.Item;
import com.ou.repositories.CabinetRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Cabinet> getAllCabinet(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Cabinet> cq = b.createQuery(Cabinet.class);

        Root<Cabinet> cabinet = cq.from(Cabinet.class);

        String active = params.get("active");
        if(active != null && !active.isEmpty()) {
            boolean ac = Boolean.parseBoolean(params.get("active"));
            Predicate activePredicate = b.equal(cabinet.get("isActive"), ac);
            cq.where(activePredicate);
        } else {
            cq.select(cabinet);
        }

        Query query = s.createQuery(cq);
        return (List<Cabinet>) query.getResultList();
    }

    @Override
    public List<Item> getItemsByCabinetId(int cabinetId, Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery<Item> cq = builder.createQuery(Item.class);
        Root item = cq.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(item.get("cabinet"), cabinetId));

        String status = params.get("status");
        if(status != null && !status.isEmpty()) {
            if(status.equalsIgnoreCase("pending")) {
                predicates.add(builder.isNull(item.get("recievedDate")));
            } else if(status.equalsIgnoreCase("received")) {
                predicates.add(builder.isNotNull(item.get("recievedDate")));
            }
        }

        cq.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(cq);
        return (List<Item>) query.getResultList();
    }
}
