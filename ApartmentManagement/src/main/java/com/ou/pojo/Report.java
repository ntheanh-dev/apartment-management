package com.ou.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Report {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = false, length = 45)
    private String title;
    @Basic
    @Column(name = "content", nullable = false, length = 45)
    private String content;
    @Basic
    @Column(name = "created_date", nullable = false)
    private Date createdDate;
    @Basic
    @Column(name = "Resident_User_id", nullable = false)
    private int residentUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

        Report report = (Report) o;

        if (id != report.id) return false;
        if (residentUserId != report.residentUserId) return false;
        if (title != null ? !title.equals(report.title) : report.title != null) return false;
        if (content != null ? !content.equals(report.content) : report.content != null) return false;
        if (createdDate != null ? !createdDate.equals(report.createdDate) : report.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + residentUserId;
        return result;
    }
}
