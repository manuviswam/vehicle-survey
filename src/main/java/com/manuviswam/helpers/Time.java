package com.manuviswam.helpers;

public class Time {

    public static int parseMilliSecondFromInput(String input){
        try {
            int milliSeconds = Integer.parseInt(input.substring(1));
            if (milliSeconds < 0 )
                return -1;
            return milliSeconds;
        }catch (NumberFormatException e){
            System.out.println("Error parsing time from : " + input);
        }
        return -1;
    }

    public static double convertToHour(int milliseconds){
        if (milliseconds <0 )
            return -1;
        int minutesInHour = com.manuviswam.constants.Time.MINUTES_IN_A_HOUR;
        int secondsInMinute = com.manuviswam.constants.Time.SECONDS_IN_A_MINUTE;
        int milliSecondsInSecond = com.manuviswam.constants.Time.MILLISECONDS_IN_A_SECOND;
        return milliseconds/(milliSecondsInSecond * secondsInMinute * minutesInHour);
    }
}
