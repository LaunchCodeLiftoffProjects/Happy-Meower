package com.happyhour.HappyHour.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class HappyHour extends AbstractEntity {


    @NotBlank
    @Size(min = 3, max = 65, message = "Name must be between 3 and 65 characters")
    private String name;

    private String dayOfWeek;

    @NotBlank
    @Size(max = 100, message = "Address too long")
    private String address;

    private int startTime;
    private int endTime;

    @ManyToOne
    @NotNull(message = "Owner is required")
    private Owner owner;

    public HappyHour(String name, String dayOfWeek, String address, int startTime, int endTime, Owner owner) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.owner = owner;
    }

    public HappyHour() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{"   + name +
                ", " + dayOfWeek +
                ", " + address +
                ", " + startTime +
                ", " + endTime +
                '}';
    }

}
