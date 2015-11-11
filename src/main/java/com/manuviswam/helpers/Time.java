package com.manuviswam.helpers;

public class Time {

    public static final int HOURS_IN_A_DAY = 24;
    public static final int MINUTES_IN_A_HOUR = 60;
    public static final int SECONDS_IN_A_MINUTE = 60;
    public static final int MILLISECONDS_IN_A_SECOND = 1000;

    public static int parseMilliSecondFromInput(String input){
        int maxMilliSecondsInADay = HOURS_IN_A_DAY * MINUTES_IN_A_HOUR * SECONDS_IN_A_MINUTE * MILLISECONDS_IN_A_SECOND;
        try {
            int milliSeconds = Integer.parseInt(input.substring(1));
            if (milliSeconds < 0 || milliSeconds > maxMilliSecondsInADay)
                return -1;
            return milliSeconds;
        }catch (NumberFormatException e){
            System.out.println("Error parsing time from : " + input);
        }
        return -1;
    }
}
