package com.happyhour.HappyHour.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.net.MalformedURLException;
import java.net.URL;
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

    private String website;


//    private static String website = getWebsite();
//    final URL happyHourURL = new URL(website);



    public HappyHour(String name, String address, List<DayTime> dayTimes, String website) {
        this.name = name;
        this.address = address;
        this.dayTimes= dayTimes;
        this.website= website;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return         name +
                ", " + address +
                ", " + getStandardDayTime() +
                ", " + website;

    }
}
