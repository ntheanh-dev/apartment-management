package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Resident_User_id", nullable = false)
    private Resident residentUser;

    @Column(name = "total_month", nullable = false)
    private Integer totalMonth;

    @Column(name = "security_deposit", nullable = false, precision = 18)
    private long securityDeposit;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "started_date", nullable = false)
    private LocalDate startedDate;

    @Column(name = "ended_date")
    private LocalDate endedDate;

}