package com.ou.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detail_evoluation", schema = "manage_apartment", catalog = "")
public class DetailEvoluation implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Criterion_id", nullable = false)
    private int criterionId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Evaluation_id", nullable = false)
    private int evaluationId;
    @Basic
    @Column(name = "Rate", nullable = true)
    private String rate;

    public int getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(int criterionId) {
        this.criterionId = criterionId;
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetailEvoluation that = (DetailEvoluation) o;

        if (criterionId != that.criterionId) return false;
        if (evaluationId != that.evaluationId) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = criterionId;
        result = 31 * result + evaluationId;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }
}
