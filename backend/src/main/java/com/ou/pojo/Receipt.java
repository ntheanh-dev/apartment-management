package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "receipt")
@NamedQueries({
        @NamedQuery(name = "Receipt.findAll", query = "select r from Receipt r")
})
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Contract_id", nullable = false)
    private Contract contract;

    @Column(name = "started_date", nullable = false)
    private LocalDate startedDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;


    @Column(name = "status")
    private String status = "Chưa thu";

    @OneToMany(mappedBy = "receipt" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ReceiptDetail> receiptDetails = new LinkedHashSet<>();


    @Column(name = "price", precision = 13, scale = 2)
    public BigDecimal price;


    @Column(name = "title", nullable = false, length = 45)
    public String title;
}