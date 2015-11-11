package com.manuviswam.model;

import com.manuviswam.constants.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionOfTheDay {
    public Date startTime;
    public Date endTime;

    private SessionOfTheDay(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static List<SessionOfTheDay> createSessionsWithInterval(int intervalInMinutes){
        List<SessionOfTheDay> sessions = new ArrayList<>();
        int totalMinutesInADay = Time.HOURS_IN_A_DAY * Time.MINUTES_IN_A_HOUR;
        if (totalMinutesInADay % intervalInMinutes != 0){
            System.out.println("Interval of " + intervalInMinutes + " minutes can not be evenly distributed in a day");
            return sessions;
        }
        for (int i=0; i< totalMinutesInADay/intervalInMinutes; i++){
            Date startTime = new Date(i * intervalInMinutes * Time.SECONDS_IN_A_MINUTE * Time.MILLISECONDS_IN_A_SECOND);
            Date endTime = new Date((i+1) * intervalInMinutes * Time.SECONDS_IN_A_MINUTE * Time.MILLISECONDS_IN_A_SECOND);

            sessions.add(new SessionOfTheDay(startTime,endTime));
        }
        return sessions;
    }
}
