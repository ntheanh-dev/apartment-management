package com.ou.pojo;

import javax.persistence.*;

@Entity
@Table(name = "member_in_room", schema = "manage_apartment", catalog = "")
public class MemberInRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Contract_id", nullable = false)
    private int contractId;
    @Basic
    @Column(name = "Resident_User_id", nullable = false)
    private int residentUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getResidentUserId() {
        return residentUserId;
    }

    public void setResidentUserId(int residentUserId) {
        this.residentUserId = residentUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberInRoom that = (MemberInRoom) o;

        if (id != that.id) return false;
        if (contractId != that.contractId) return false;
        if (residentUserId != that.residentUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + contractId;
        result = 31 * result + residentUserId;
        return result;
    }
}
