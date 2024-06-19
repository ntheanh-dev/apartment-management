package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "room")
@NamedQueries({
        @NamedQuery(name = "Room.findAll", query = "select r from Room r"),
        @NamedQuery(name = "Room.countByStatusLike", query = "select count(r) from Room r where r.status like :status")
})
public class Room {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", nullable = false, length = 10)
    private String number;

    @Column(name = "Price", nullable = false, precision = 18)
    private Long price;

    @Column(name = "length", nullable = false)
    private Float length;

    @Column(name = "width", nullable = false)
    private Float width;

    @Column(name = "maximum", nullable = false)
    private Integer maximum;

    @Column(name = "description", length = 50)
    private String description;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Floor_id", nullable = false)
    private Floor floor;

}