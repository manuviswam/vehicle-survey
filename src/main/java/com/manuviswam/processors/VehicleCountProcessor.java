package com.manuviswam.processors;

import com.manuviswam.constants.App;
import com.manuviswam.model.Direction;
import com.manuviswam.model.SessionOfTheDay;
import com.manuviswam.model.VehicleEntry;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleCountProcessor extends SessionViceProcessor {
    int interval; //in minutes

    public VehicleCountProcessor(int interval) {
        this.interval = interval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        SessionOfTheDay peakSession = null;
        int peakVehicleCount = 0;
        StringBuilder sb = new StringBuilder();

        List<SessionOfTheDay> sessions = SessionOfTheDay.createSessionsWithInterval(interval);
        if (sessions.size() < 1)
            return "";
        for(SessionOfTheDay session : sessions){
            int totalCountAcrossDays = 0;
            List<VehicleEntry> sessionEntries = getEntriesInTheSession(entries, session);
            sb.append(session.toString());
            for (int day = 0; day < App.NUMBER_OF_DAYS; day++) {
                int southCount = countEntriesForTheDayForDirection(sessionEntries, day, Direction.SOUTH);
                int northCount = countEntriesForTheDayForDirection(sessionEntries, day, Direction.NORTH);
                sb.append(" | Day " + day + " Count South = " + southCount + " North = " + northCount);
                totalCountAcrossDays += southCount + northCount;
            }
            sb.append("\n");
            if (totalCountAcrossDays > peakVehicleCount){
                peakVehicleCount = totalCountAcrossDays;
                peakSession = session;
            }
        }
        sb.append("\n" + peakSession + " is peak session with a vehicle count of " + peakVehicleCount + " across " + App.NUMBER_OF_DAYS + " days");
        return sb.toString();
    }

    private int countEntriesForTheDayForDirection(List<VehicleEntry> sessionEntries, int day, Direction direction) {
        return sessionEntries.stream().filter(entry -> entry.getDay() == day && entry.getDirection() == direction).collect(Collectors.toList()).size();
    }
}
