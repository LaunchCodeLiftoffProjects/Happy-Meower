package com.happyhour.HappyHour.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class HappyHour extends AbstractEntity {

    @NotBlank
    @Size(min = 3, max = 65, message = "Name must be between 3 and 65 characters")
    private String name;

    @ManyToMany
    private List<DayTime> dayTimes;

    @NotBlank
    @Size(max = 100, message = "Address too long")
    private String address;


    @ManyToOne
    private Owner owner;

    public HappyHour(String name, String address, Owner owner, List<DayTime> dayTimes) {
        this.name = name;
        this.address = address;
        this.dayTimes=dayTimes;
        this.owner = owner;
    }

    public HappyHour() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DayTime> getDayTimes() {
        return dayTimes;
    }

    public void setDayTimes(List<DayTime> dayTimes) { this.dayTimes = dayTimes; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStandardDayTime(){
        return HourData.getStandardDayTime(dayTimes);
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return         name +
                ", " + address +
                ", " + getStandardDayTime();

    }
}
