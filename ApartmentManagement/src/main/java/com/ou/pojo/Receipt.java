package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Contract_id", nullable = false)
    private Contract contract;

    @Column(name = "started_date", nullable = false)
    private LocalDate startedDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private String title;

    @Column(name = "status")
    private String status = "Chưa thu";

//    private Set<ReceiptDetail> receiptDetails = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "receipt")
//    public Set<ReceiptDetail> getReceiptDetails() {
//        return receiptDetails;
//    }

    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }
}