package com.ou.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    private Integer id;

    private String username;

    private String password;

    private String role;

    private Boolean active;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    @Lob
    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

}