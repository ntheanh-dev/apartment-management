package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty(message = "(*): Thông tin bắt buộc")
    private String number;

    @Column(name = "Price", nullable = false, precision = 18)
    @NotNull(message = "(*): Thông tin bắt buộc")
    private Long price;

    @Column(name = "length", nullable = false)
    @NotNull(message = "(*): Thông tin bắt buộc")
    private Float length;

    @Column(name = "width", nullable = false)
    @NotNull(message = "(*): Thông tin bắt buộc")
    private Float width;

    @Column(name = "maximum", nullable = false)
    @NotNull(message = "(*): Thông tin bắt buộc")
    private Integer maximum;

    @Column(name = "description", length = 50)
    @NotEmpty(message = "(*): Thông tin bắt buộc")
    private String description;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Floor_id", nullable = false)
    @Valid
    private Floor floor;

}