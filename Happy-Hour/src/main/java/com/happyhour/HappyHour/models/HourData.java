package com.happyhour.HappyHour.models;

import com.happyhour.HappyHour.data.DayTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HourData {

    @Autowired
    private DayTimeRepository dayTimeRepository;
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

    /**
     * Converts a list of dayTime and returns a string
     *
     * @param dayTimes list of day times stored in happy hours
     * @return          String format for daytime(DayOfWeek: startTime - EndTime|...)
     */
    public static String getStandardDayTime(List<DayTime> dayTimes){
        StringBuilder temp= new StringBuilder(dayTimes.get(0).getDayOfWeek().toString() + ":" + HourData.getStandardTime(dayTimes.get(0).getTime()));//stores the first entry
        int counter=0; //stores the index of the last entry in a block

        /*
        Step through all the dayTime and check to see if they are in a continuous block.
        Starts at the second entry.
         */
        for(int i=1;i<dayTimes.size();i++){
            if(dayTimes.get(i).getId()!=dayTimes.get(i-1).getId()+1) { //checks to see if current id is not the next entry(monday @ 0000=1 monday @ 0300=4)
                temp.append(" - ").append(HourData.getStandardTime(dayTimes.get(counter).getTime() + 100)).append("|");//add the time for the last block
                temp.append(dayTimes.get(i).getDayOfWeek().toString()).append(":").append(HourData.getStandardTime(dayTimes.get(i).getTime()));//starts the new block
            }else {
                counter = i;        //stores the last index of a continuous block
            }
        }
        temp.append(" - ").append(HourData.getStandardTime(dayTimes.get(dayTimes.size() - 1).getTime() + 100));//add the final time
        return temp.toString();
    }

    /**
     * Converts military time(int) to standard time(string)
     * Doesn't account for 2400
     * @param time Military Time
     * @return     String of Standard time from militarty(0=12am - 2300=11pm)
     */
    public static String getStandardTime(int time){
        String temp="";
        if((time/100)%12==0){
            temp+="12";
        }else{
            temp+=Integer.toString((time/100)%12);
        }
        if(time<1200){
            temp+=" am";
        }else{
            temp+=" pm";
        }
        return temp;
    }
}
