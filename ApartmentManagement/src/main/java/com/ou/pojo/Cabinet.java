package com.ou.pojo;

import javax.persistence.*;

@Entity
public class Cabinet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "cabinetcol", nullable = true, length = 45)
    private String cabinetcol;
    @Basic
    @Column(name = "Contract_id", nullable = false)
    private int contractId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCabinetcol() {
        return cabinetcol;
    }

    public void setCabinetcol(String cabinetcol) {
        this.cabinetcol = cabinetcol;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cabinet cabinet = (Cabinet) o;

        if (id != cabinet.id) return false;
        if (contractId != cabinet.contractId) return false;
        if (cabinetcol != null ? !cabinetcol.equals(cabinet.cabinetcol) : cabinet.cabinetcol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cabinetcol != null ? cabinetcol.hashCode() : 0);
        result = 31 * result + contractId;
        return result;
    }
}
