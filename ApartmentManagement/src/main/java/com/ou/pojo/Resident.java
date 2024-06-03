package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "User_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "gender", nullable = false)
    private Boolean gender = false;

    @Column(name = "avatar", nullable = false, length = 50)
    private String avatar;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(name = "number_plate", length = 10)
    private String numberPlate;

    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @Column(name = "ward", nullable = false, length = 45)
    private String ward;

    @Column(name = "address", nullable = false, length = 45)
    private String address;

    @Column(name = "date_of_birth", nullable = false, length = 45)
    private String dateOfBirth;

    @Column(name = "identity", nullable = false, length = 12)
    private String identity;

}