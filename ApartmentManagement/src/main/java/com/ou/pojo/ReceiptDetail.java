package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "receipt_detail")
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Services_id", nullable = false)
    private Service services;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Receipt_id", nullable = false)
    private Receipt receipt;

    @Column(name = "amount")
    private Integer amount;

}