package com.ou.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Items {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = 45)
    private String description;
    @Basic
    @Column(name = "delivery_date", nullable = true)
    private Date deliveryDate;
    @Basic
    @Column(name = "cabinet_id", nullable = false)
    private int cabinetId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(int cabinetId) {
        this.cabinetId = cabinetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Items items = (Items) o;

        if (id != items.id) return false;
        if (cabinetId != items.cabinetId) return false;
        if (name != null ? !name.equals(items.name) : items.name != null) return false;
        if (description != null ? !description.equals(items.description) : items.description != null) return false;
        if (deliveryDate != null ? !deliveryDate.equals(items.deliveryDate) : items.deliveryDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + cabinetId;
        return result;
    }
}
