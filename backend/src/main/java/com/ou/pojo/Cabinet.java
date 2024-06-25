package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cabinet")
@NamedQueries({
        @NamedQuery(name = "Cabinet.findByContract_Id", query = "select c from Cabinet c where c.contract.id = :id")
})
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cabinetcol", length = 45)
    private String cabinetcol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Contract_id", nullable = false)
    private Contract contract;

    @Column(name = "isActive")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "cabinet")
    private List<Item> items;

}