package com.happyhour.HappyHour.models;

import java.util.ArrayList;

public class HourData {

    /**
     * Search all of happy hour by search term
     *
     * @param searchTerm The search term to look for.
     * @param allHappyHours The list of happy hours to search.
     * @return      List of all happy hours with the search term.
     */
    public static ArrayList<HappyHour> searchHappyHour(String searchTerm, Iterable<HappyHour> allHappyHours){
        ArrayList<HappyHour> temp= new ArrayList<>();
        for (HappyHour happyHour : allHappyHours) {
            if (happyHour.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                temp.add(happyHour);
            }
        }
        return temp;
    }
}
