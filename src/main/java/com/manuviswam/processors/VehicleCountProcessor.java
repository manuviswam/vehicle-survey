package com.manuviswam.processors;

import com.manuviswam.constants.App;
import com.manuviswam.model.SessionOfTheDay;
import com.manuviswam.model.VehicleEntry;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleCountProcessor implements IDataProcessor {
    int interval; //in minutes

    public VehicleCountProcessor(int interval) {
        this.interval = interval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();
        List<SessionOfTheDay> sessions = SessionOfTheDay.createSessionsWithInterval(interval);
        for(SessionOfTheDay session : sessions){
            List<VehicleEntry> sessionEntries = getEntriesInTheSession(entries, session);
            sb.append(session.toString());
            for (int day = 0; day < App.NUMBER_OF_DAYS; day++) {
                int count = countEntriesForTheDay(sessionEntries,day);
                sb.append(" | Day " + day + " Count=" + count);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int countEntriesForTheDay(List<VehicleEntry> sessionEntries, int day) {
        return sessionEntries.stream().filter(entry -> entry.getDay() == day).collect(Collectors.toList()).size();
    }

    private List<VehicleEntry> getEntriesInTheSession(List<VehicleEntry> entries, SessionOfTheDay session) {
        return entries.stream().
                filter(entry -> entry.entryTime().compareTo(session.startTime) >= 0
                        && entry.entryTime().compareTo(session.endTime) < 0)
                .collect(Collectors.toList());
    }
}
