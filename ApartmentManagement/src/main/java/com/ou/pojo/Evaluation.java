package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "evaluation")
@NamedQueries({
        @NamedQuery(name = "Evaluation.findAll", query = "select e from Evaluation e")
})
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "feedback", length = 45)
    private String feedback;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Resident_User_id", nullable = false)
    @JsonIgnore
    private Resident residentUser;

    @JsonIgnore
    @OneToMany(mappedBy = "evaluation" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<DetailEvoluation> detailEvoluations = new LinkedHashSet<>();

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
}