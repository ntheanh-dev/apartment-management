package com.ou.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Contract {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Room_id", nullable = false)
    private int roomId;
    @Basic
    @Column(name = "Resident_User_id", nullable = false)
    private int residentUserId;
    @Basic
    @Column(name = "total_month", nullable = false)
    private int totalMonth;
    @Basic
    @Column(name = "security_deposit", nullable = false, precision = 0)
    private int securityDeposit;
    @Basic
    @Column(name = "created_date", nullable = false)
    private Date createdDate;
    @Basic
    @Column(name = "started_date", nullable = false)
    private Date startedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getResidentUserId() {
        return residentUserId;
    }

    public void setResidentUserId(int residentUserId) {
        this.residentUserId = residentUserId;
    }

    public int getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(int totalMonth) {
        this.totalMonth = totalMonth;
    }

    public int getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(int securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        if (id != contract.id) return false;
        if (roomId != contract.roomId) return false;
        if (residentUserId != contract.residentUserId) return false;
        if (totalMonth != contract.totalMonth) return false;
        if (securityDeposit != contract.securityDeposit) return false;
        if (createdDate != null ? !createdDate.equals(contract.createdDate) : contract.createdDate != null)
            return false;
        if (startedDate != null ? !startedDate.equals(contract.startedDate) : contract.startedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roomId;
        result = 31 * result + residentUserId;
        result = 31 * result + totalMonth;
        result = 31 * result + securityDeposit;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (startedDate != null ? startedDate.hashCode() : 0);
        return result;
    }
}
