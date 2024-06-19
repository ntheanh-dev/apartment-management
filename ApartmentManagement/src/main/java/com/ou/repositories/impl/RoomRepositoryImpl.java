package com.ou.repositories.impl;

import com.ou.pojo.Room;
import com.ou.repositories.RoomRepository;
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
public class RoomRepositoryImpl implements RoomRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public void addOrUpdateRoom(Room room) {
        Session s = this.factory.getObject().getCurrentSession();
        if(room.getId() != null){
            s.update(room);
        }else{
            s.save(room);
        }
    }

    @Override
    public List<Room> getAllRooms(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Room> q = b.createQuery(Room.class);
        Root r = q.from(Room.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("number"), String.format("%%%s%%", kw)));
        }
        String status = params.get("status");
        if (status != null && !status.equals("all")) {
            predicates.add(b.like(r.get("status"), String.format("%%%s%%", status)));
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        List<Room> rooms = query.getResultList();

        return rooms;
    }

    @Override
    public Room getRoomById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Room.class, id);
    }

    @Override
    public void deleteRoomById(Room room) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(room);
    }

    @Override
    public void updateStatus(Room room) {
        Session s = this.factory.getObject().getCurrentSession();
        Room tmp = this.getRoomById(room.getId());
        tmp.setStatus("đã thuê");
        s.update(tmp);
    }

    @Override
    public Long getRoomCount() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Room.countByStatusLike");
        q.setParameter("status", "đã thuê");
        return (Long) q.getSingleResult();
    }
}
