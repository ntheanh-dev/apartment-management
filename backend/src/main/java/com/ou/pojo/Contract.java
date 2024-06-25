package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "contract")
@NamedQueries({
        @NamedQuery(name = "Contract.findByRoom_IdOrderByCreatedDateDesc", query = "select c from Contract c where c.room.id = :id order by c.createdDate DESC"),
        @NamedQuery(name = "Contract.findByEndedDateLessThan", query = "select c from Contract c where c.endedDate > :endedDate")
})
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Room_id", nullable = false)
    private Room room;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Resident_User_id", nullable = false)
    private Resident residentUser;

    @Column(name = "total_month", nullable = false)
    private Integer totalMonth;

    @Column(name = "security_deposit", nullable = false, precision = 18)
    private long securityDeposit;

    @Column(name = "created_date", nullable = false)
    private String createdDate;

    @Column(name = "started_date", nullable = false)
    private String startedDate;

    @Column(name = "ended_date")
    private LocalDate endedDate;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract",fetch = FetchType.EAGER)
    private Set<MemberInRoom> memberInRoom;
}