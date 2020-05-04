package com.happyhour.HappyHour.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class HourData {
    public static HappyHour findHHById(int id, Map<Integer,HappyHour> happyHours){
        return happyHours.get(id);
    }

    public static ArrayList<HappyHour> searchHappyHour(String searchTerm, ArrayList<HappyHour> allHappyHours){
        ArrayList<HappyHour> temp= new ArrayList<>();
        for (HappyHour happyHour : allHappyHours) {
            if (happyHour.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                temp.add(happyHour);
            }
        }
        return temp;
    }
}
