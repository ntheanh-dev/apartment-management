package com.ou.pojo;

import javax.persistence.*;

@Entity
@Table(name = "receipt_detail", schema = "manage_apartment", catalog = "")
public class ReceiptDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Services_id", nullable = false)
    private int servicesId;
    @Basic
    @Column(name = "Receipt_id", nullable = false)
    private int receiptId;
    @Basic
    @Column(name = "amount", nullable = true)
    private Integer amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiptDetail that = (ReceiptDetail) o;

        if (id != that.id) return false;
        if (servicesId != that.servicesId) return false;
        if (receiptId != that.receiptId) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + servicesId;
        result = 31 * result + receiptId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
