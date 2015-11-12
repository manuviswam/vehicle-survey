package com.manuviswam.processors;

import com.manuviswam.model.SessionOfTheDay;
import com.manuviswam.model.VehicleEntry;

import java.util.List;

public class SpeedDistributionProcessor extends BaseProcessor {
    int sessionInterval;

    public SpeedDistributionProcessor(int sessionInterval) {
        this.sessionInterval = sessionInterval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();

        List<SessionOfTheDay> sessions = SessionOfTheDay.createSessionsWithInterval(sessionInterval);
        for (SessionOfTheDay session : sessions){
            List<VehicleEntry> entriesInTheSession = getEntriesInTheSession(entries, session);
            double totalSpeed = entriesInTheSession.stream().mapToDouble(entry -> entry.speedInKMPH()).sum();
            double averageSpeed = 0;
            if (entriesInTheSession.size() > 0)
                averageSpeed = totalSpeed/entriesInTheSession.size();
            sb.append(session + " | Average speed = " + averageSpeed + "\n");
        }
        return sb.toString();
    }
}
