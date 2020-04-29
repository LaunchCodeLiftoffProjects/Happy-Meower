package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.HappyHour;

import java.util.HashMap;
import java.util.Map;

public class HappyHourData {

    //need a place to to put happy hours
    private static final Map<Integer, HappyHour> happyHours = new HashMap<>();

    //get happy hour by location


    //add a happy hour
    public static void add(HappyHour happyHour) {
        happyHours.put(happyHour.getId(), happyHour);
    }
    //remove a happy hour
    public static void remove(int id) {
        happyHours.remove(id);
    }
}
