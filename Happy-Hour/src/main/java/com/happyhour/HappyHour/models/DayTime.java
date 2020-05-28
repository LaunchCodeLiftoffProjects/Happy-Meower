package com.happyhour.HappyHour.models;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;

@Entity
public class DayTime {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private int time;

    @ManyToMany(mappedBy = "dayTimes")
    private List<HappyHour> happyHours;

    public DayTime() {}

    public DayTime(DayOfWeek dayOfWeek, int time) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHappyHours(List<HappyHour> happyHours) { this.happyHours = happyHours; }

    public List<HappyHour> getHappyHours() { return happyHours; }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return dayOfWeek.toString()+HourData.getStandardTime(time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayTime that = (DayTime) o;
        return id == that.id;
    }
}
