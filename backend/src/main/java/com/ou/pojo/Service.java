package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "services")
@NamedQueries({
        @NamedQuery(name = "Service.findAll", query = "select s from Service s")
})
public class Service {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "price", nullable = false, length = 45)
    private String price;

    @Column(name = "unit", nullable = false, length = 10)
    private String unit;

}