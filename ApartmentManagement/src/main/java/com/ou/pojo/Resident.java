package com.ou.pojo;

import javax.persistence.*;

@Entity
public class Resident {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "User_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;
    @Basic
    @Column(name = "active", nullable = false)
    private byte active;
    @Basic
    @Column(name = "gender", nullable = false)
    private byte gender;
    @Basic
    @Column(name = "avatar", nullable = false, length = 50)
    private String avatar;
    @Basic
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;
    @Basic
    @Column(name = "email", nullable = false, length = 20)
    private String email;
    @Basic
    @Column(name = "number_plate", nullable = true, length = 10)
    private String numberPlate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resident resident = (Resident) o;

        if (userId != resident.userId) return false;
        if (active != resident.active) return false;
        if (gender != resident.gender) return false;
        if (fullName != null ? !fullName.equals(resident.fullName) : resident.fullName != null) return false;
        if (avatar != null ? !avatar.equals(resident.avatar) : resident.avatar != null) return false;
        if (phone != null ? !phone.equals(resident.phone) : resident.phone != null) return false;
        if (email != null ? !email.equals(resident.email) : resident.email != null) return false;
        if (numberPlate != null ? !numberPlate.equals(resident.numberPlate) : resident.numberPlate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (int) active;
        result = 31 * result + (int) gender;
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (numberPlate != null ? numberPlate.hashCode() : 0);
        return result;
    }
}
