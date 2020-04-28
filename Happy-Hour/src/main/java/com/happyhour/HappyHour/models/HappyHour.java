package com.happyhour.HappyHour.models;

public class HappyHour {

    private String name;
    private String dayOfWeek;
    private String address;
    private int startTime;
    private int endTime;

    public HappyHour(String name, String dayOfWeek, String address, int startTime, int endTime) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
    }

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

    @Override
    public toString() {
        return "HappyHour{" +
                "name='" + name + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", address='" + address + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
