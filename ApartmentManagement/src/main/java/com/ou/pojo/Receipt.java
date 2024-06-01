package com.ou.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Receipt {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Contract_id", nullable = false)
    private int contractId;
    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    private int total;
    @Basic
    @Column(name = "started_date", nullable = false)
    private Date startedDate;
    @Basic
    @Column(name = "end_date", nullable = false)
    private Date endDate;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (id != receipt.id) return false;
        if (contractId != receipt.contractId) return false;
        if (total != receipt.total) return false;
        if (startedDate != null ? !startedDate.equals(receipt.startedDate) : receipt.startedDate != null) return false;
        if (endDate != null ? !endDate.equals(receipt.endDate) : receipt.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + contractId;
        result = 31 * result + total;
        result = 31 * result + (startedDate != null ? startedDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
