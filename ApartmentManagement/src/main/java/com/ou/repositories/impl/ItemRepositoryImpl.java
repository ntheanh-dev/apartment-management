package com.ou.repositories.impl;
import com.ou.dto.request.PaginationRequest;
import com.ou.dto.response.ItemResponse;
import com.ou.dto.response.PaginationResponse;
import com.ou.mapper.CabinetMapper;
import com.ou.pojo.*;
import com.ou.repositories.ItemRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@PropertySource("classpath:app.properties")
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Autowired
    private CabinetMapper cabinetMapper;

    @Override
    public void addOrUpdateItem(Item item) {
        Session s = this.factory.getObject().getCurrentSession();
        if(item.getId() != null) {
            s.update(item);
        } else {
            s.save(item);
        }
    }

    @Override
    public void addItem(Item item) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(item);
    }

//    select item0_.id as id1_8_,
//    item0_.cabinet_id as cabinet_7_8_,
//    item0_.delivery_date as delivery2_8_,
//    item0_.description as descript3_8_,
//    item0_.image as image4_8_,
//    item0_.name as name5_8_,
//    item0_.received_date as received6_8_ from items item0_
//    inner join cabinet cabinet1_ on item0_.cabinet_id=cabinet1_.id
//    inner join contract contract2_ on cabinet1_.Contract_id=contract2_.id
//    inner join member_in_room memberinro3_ on contract2_.id=memberinro3_.Contract_id
//    where memberinro3_.Resident_User_id=135
    @Override
    public PaginationResponse<ItemResponse> getItemsInMyRoom(PaginationRequest paginationRequest) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Item> cq = builder.createQuery(Item.class);

        Root<Item> itemRoot = cq.from(Item.class);
        Join<Item, Cabinet> joinCabinet = itemRoot.join("cabinet", JoinType.INNER);
        Join<Cabinet,Contract> joinContract = joinCabinet.join("contract", JoinType.INNER);
        Join<Contract,MemberInRoom> joinMember = joinContract.join("memberInRoom", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(joinMember.get("residentUser"), u.getId()));

        cq.select(itemRoot).where(predicates.toArray(Predicate[]::new));
        //-----------------order----------------
//        if(paginationRequest != null) {
//            if(!paginationRequest.getSortBy().isEmpty()) {
//                Field[] fields = Item.class.getDeclaredFields();
//                String fieldName = paginationRequest.getSortBy();
//                for (Field field : fields) {
//                    if(fieldName.equals(field.getName())) {
//                        String order = paginationRequest.getOrder().toLowerCase();
//                        if(order.equals("asc")) {
//                            cq.orderBy(builder.asc(itemRoot.get(field.getName())));
//                        } else {
//                            cq.orderBy(builder.desc(itemRoot.get(field.getName())));
//                        }
//                        break;
//                    }
//                }
//            }
//        }
        List<Item> items = s.createQuery(cq).getResultList();
        //--------------pagination-----------------
//        if(paginationRequest != null) {
//            if(paginationRequest.getPage()!=0) {
//                int pageSize = Integer.parseInt(env.getProperty("app.pageSize").toString());
//            }
//        }

        return PaginationResponse.<ItemResponse>builder()
                .results(cabinetMapper.toCabinetItemResponseList(items))
                .build();
    }
}
