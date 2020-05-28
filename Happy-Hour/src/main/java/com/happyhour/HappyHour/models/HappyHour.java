package com.happyhour.HappyHour.models;

import javax.persistence.Entity;
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


    public HappyHour(String name, String address, List<DayTime> dayTimes) {
        this.name = name;
        this.address = address;
        this.dayTimes=dayTimes;
    }

    public HappyHour() { }

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

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getStandardDayTime(){
        return HourData.getStandardDayTime(dayTimes);
    }

    @Override
    public String toString() {
        String temp="";
        return "{"   + name +
                ", " + address +
                ", " + getStandardDayTime() +
                '}';
    }
}
