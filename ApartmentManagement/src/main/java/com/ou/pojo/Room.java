package com.ou.pojo;

import javax.persistence.*;

@Entity
public class Room {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "number", nullable = false, length = 10)
    private String number;
    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    private int price;
    @Basic
    @Column(name = "length", nullable = false, precision = 0)
    private double length;
    @Basic
    @Column(name = "width", nullable = false, precision = 0)
    private double width;
    @Basic
    @Column(name = "maximum", nullable = false)
    private int maximum;
    @Basic
    @Column(name = "description", nullable = true, length = 50)
    private String description;
    @Basic
    @Column(name = "status", nullable = false)
    private String status;
    @Basic
    @Column(name = "Floor_id", nullable = false)
    private int floorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (price != room.price) return false;
        if (Double.compare(length, room.length) != 0) return false;
        if (Double.compare(width, room.width) != 0) return false;
        if (maximum != room.maximum) return false;
        if (floorId != room.floorId) return false;
        if (number != null ? !number.equals(room.number) : room.number != null) return false;
        if (description != null ? !description.equals(room.description) : room.description != null) return false;
        if (status != null ? !status.equals(room.status) : room.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + price;
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + maximum;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + floorId;
        return result;
    }
}
