package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "report")
@NamedQueries({
        @NamedQuery(name = "Report.findByOrderByCreatedDateDesc", query = "select r from Report r order by r.createdDate DESC")
})
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "content", nullable = false, length = 45)
    private String content;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Resident_User_id", nullable = false)
    @JsonIgnore
    private Resident residentUser;

    private String status;

    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }
}