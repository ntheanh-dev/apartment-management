package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "member_in_room")
@NamedQueries({
        @NamedQuery(name = "MemberInRoom.findByContract_Id", query = "select m from MemberInRoom m where m.contract.id = :id"),
        @NamedQuery(name = "MemberInRoom.findByContract_IdAndResidentUser_Id", query = "select m from MemberInRoom m where m.contract.id = :id1 and m.residentUser.id = :id2")
})
public class MemberInRoom {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Contract_id", nullable = false)
    private Contract contract;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Resident_User_id", nullable = false)
    private Resident residentUser;

}