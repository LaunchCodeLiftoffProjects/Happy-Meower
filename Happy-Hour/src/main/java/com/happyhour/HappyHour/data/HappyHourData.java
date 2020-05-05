package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.HappyHour;

import java.util.HashMap;

public class HappyHourData {

    private static int id=1;

    //need a place to to put happy hours
    private static final HashMap<Integer, HappyHour> happyHours = new HashMap<>();

    public HappyHourData(){

        //temporary data to be deleted after integration
        HappyHour temp1=new HappyHour("Bar 1", "Monday-Friday","321 Main St.", 14,16);
        add(temp1);
        HappyHour temp2=new HappyHour("Bar 2", "Tuesday-Friday","123 Broadway Ave.", 14,16);
        add(temp2);
        HappyHour temp3=new HappyHour("Bar 3", "Wednesday-Thursday","5312 14th st.", 14,16);
        add(temp3);
        HappyHour temp4=new HappyHour("Bar 4", "Tuesday-Friday","2314 Prospect Ave", 14,16);
        add(temp4);
        HappyHour temp5=new HappyHour("Bar 5", "Saturday-Sunday","111 One st.", 14,16);
        add(temp5);

    }

    //get happy hour by location
    public static HashMap<Integer, HappyHour> getHappyHours() {
        return happyHours;
    }

    //add a happy hour
    public static void add(HappyHour happyHour) {
        happyHour.setId(id);
        happyHours.put(id, happyHour);
        id++;
    }

    //remove a happy hour
    public static void remove(int id) {
        happyHours.remove(id);
    }
}
