package com.happyhour.HappyHour.models.dto;

import com.happyhour.HappyHour.models.DayTime;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


/**
 * Temporary form for thymeleaf to handle conversion of entries to store in happyhour
 */
public class TimeFormDTO {

    @NotNull
    private DayOfWeek[] dayOfWeek;

    private int[] startTime;

    private int[] endTime;

    public DayOfWeek[] getDayOfWeek() { return dayOfWeek; }

    public void setStartTime(int[] startTime) { this.startTime = startTime; }

    public int[] getEndTime() { return endTime; }

    public int[] getStartTime() { return startTime; }

    public void setDayOfWeek(DayOfWeek[] dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public void setEndTime(int[] endTime) { this.endTime = endTime; }

    //For testing can be removed
    public String getStandard(){
        StringBuilder temp= new StringBuilder();
        for (int i=0;i<dayOfWeek.length;i++){
            temp.append(i).append(dayOfWeek[i]).append(startTime[i]).append(endTime[i]);
        }
        return temp.toString();
    }

    /**
     * Converts TimeFormDTO to a list of daytime to be stored
     *
     * @param  iterator daytime repository
     * @return          List of dayTimes
     */
    public List<DayTime> getAllDayTimes(Iterable<DayTime> iterator){
        List<DayTime> allDayTime=new ArrayList<>();             //
        iterator.forEach(allDayTime::add);                      //converts iterator to list
        List<DayTime>temp=new ArrayList<>();                    //temp list to store everything

        /**
         Iterates through the day of week stored(monday - sunday; whichever is stored)
         add every section of time block
         Formula for getting daytime
         day of week(monday=1~sunday=7) * 24(number of blocks) + time(0000-2300) / 100(removes military time)
        */
        for (DayOfWeek day : dayOfWeek) {
            int timeBetween = (endTime[day.getValue() - 1] - startTime[day.getValue() - 1]) / 100;  //calculated difference and covert time from military time
            /*
            Iterated through start time to end time
            */
            for (int a = 0; a < timeBetween; a++) {
                temp.add(allDayTime.get(((day.getValue() - 1) * 24) + (startTime[day.getValue() - 1] / 100) + a));
            }
        }
        return temp;
    }

    public String getTimeString(){
        StringBuilder temp= new StringBuilder();
        for (DayOfWeek day : dayOfWeek) {
            temp.append(day).append(" ").append(startTime[day.getValue() - 1]).append(" ").append(endTime[day.getValue() - 1]);
        }

        return temp.toString();
    }
}
