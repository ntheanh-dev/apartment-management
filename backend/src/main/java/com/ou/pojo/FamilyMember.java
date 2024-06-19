package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "family_member")
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "active", nullable = false, length = 1)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Resident_User_id", nullable = false)
    @JsonIgnore
    private Resident residentUser;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "relationship_type", nullable = false)
    private String relationshipType;
}