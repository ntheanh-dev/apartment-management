package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "resident")
@NamedQueries({
        @NamedQuery(name = "Resident.existsByUser_Id", query = "select (count(r) > 0) from Resident r where r.user.id = :id"),
        @NamedQuery(name = "Resident.findByUser_RoleAndUser_Active", query = "select r from Resident r where r.user.role = 'ROLE_USER' and r.user.active = true ")
})
public class Resident {
    @Id
    @Column(name = "User_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(name = "gender")
    private Boolean gender = false;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "number_plate", length = 10)
    private String numberPlate;

    @Column(name = "city", length = 45)
    private String city;

    @Column(name = "ward", length = 45)
    private String ward;

    @Column(name = "address", nullable = false, length = 45)
    private String address;

    @Column(name = "date_of_birth", nullable = false, length = 45)
    private LocalDate dateOfBirth;

    @Column(name = "identity", nullable = false, length = 20)
    private String identity;

    @OneToMany(mappedBy = "residentUser",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evaluation> evaluations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "residentUser",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FamilyMember> familyMembers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "residentUser",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberInRoom> memberInRooms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "residentUser",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Report> reports = new LinkedHashSet<>();

}