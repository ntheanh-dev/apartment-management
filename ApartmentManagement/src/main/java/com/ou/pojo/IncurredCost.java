package com.ou.pojo;

import javax.persistence.*;

@Entity
@Table(name = "incurred_cost", schema = "manage_apartment", catalog = "")
public class IncurredCost {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 10)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 45)
    private String description;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Integer price;
    @Basic
    @Column(name = "Incurred_costcol", nullable = true, length = 45)
    private String incurredCostcol;
    @Basic
    @Column(name = "Receipt_id", nullable = false)
    private int receiptId;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getIncurredCostcol() {
        return incurredCostcol;
    }

    public void setIncurredCostcol(String incurredCostcol) {
        this.incurredCostcol = incurredCostcol;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncurredCost that = (IncurredCost) o;

        if (id != that.id) return false;
        if (receiptId != that.receiptId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (incurredCostcol != null ? !incurredCostcol.equals(that.incurredCostcol) : that.incurredCostcol != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (incurredCostcol != null ? incurredCostcol.hashCode() : 0);
        result = 31 * result + receiptId;
        return result;
    }
}
