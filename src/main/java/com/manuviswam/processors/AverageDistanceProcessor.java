package com.manuviswam.processors;

import com.manuviswam.constants.App;
import com.manuviswam.constants.Time;
import com.manuviswam.model.SessionOfTheDay;
import com.manuviswam.model.VehicleEntry;

import java.util.List;

public class AverageDistanceProcessor extends BaseProcessor {
    int sessionInterval;

    public AverageDistanceProcessor(int sessionInterval) {
        this.sessionInterval = sessionInterval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();

        List<SessionOfTheDay> sessions = SessionOfTheDay.createSessionsWithInterval(sessionInterval);
        for (SessionOfTheDay session : sessions){
            List<VehicleEntry> entriesInTheSession = getEntriesInTheSession(entries, session);
            double distance = getAverageDistance(entriesInTheSession);
            sb.append(session + " | Average distance between cars = " + distance + " meters\n");
        }
        return sb.toString();
    }

    private double getAverageDistance(List<VehicleEntry> entriesInTheSession) {
        if (entriesInTheSession.size() < 1)
            return 0;
            long timeOfLastVehicle = entriesInTheSession.get(entriesInTheSession.size() - 1).entryTime().getTime();
            long timeOfFirstVehicle = entriesInTheSession.get(0).entryTime().getTime();
            double averageTimeGapBetweenVehicles = ((double) timeOfLastVehicle - (double) timeOfFirstVehicle) / (double)entriesInTheSession.size();
            return averageTimeGapBetweenVehicles * App.AVERAGE_SPEED/ Time.MILLISECONDS_IN_A_SECOND;
    }
}
