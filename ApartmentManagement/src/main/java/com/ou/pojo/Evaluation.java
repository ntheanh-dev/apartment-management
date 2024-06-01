package com.ou.pojo;

import javax.persistence.*;

@Entity
public class Evaluation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Evaluationcol", nullable = true, length = 45)
    private String evaluationcol;
    @Basic
    @Column(name = "feedback", nullable = true, length = 45)
    private String feedback;
    @Basic
    @Column(name = "Resident_User_id", nullable = false)
    private int residentUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvaluationcol() {
        return evaluationcol;
    }

    public void setEvaluationcol(String evaluationcol) {
        this.evaluationcol = evaluationcol;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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

        Evaluation that = (Evaluation) o;

        if (id != that.id) return false;
        if (residentUserId != that.residentUserId) return false;
        if (evaluationcol != null ? !evaluationcol.equals(that.evaluationcol) : that.evaluationcol != null)
            return false;
        if (feedback != null ? !feedback.equals(that.feedback) : that.feedback != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (evaluationcol != null ? evaluationcol.hashCode() : 0);
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        result = 31 * result + residentUserId;
        return result;
    }
}
