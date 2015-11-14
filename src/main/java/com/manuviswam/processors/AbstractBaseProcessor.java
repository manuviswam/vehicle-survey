package com.manuviswam.processors;

import com.manuviswam.model.Direction;
import com.manuviswam.model.SessionOfTheDay;
import com.manuviswam.model.VehicleEntry;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractBaseProcessor implements IDataProcessor {
    @Override
    public abstract String process(List<VehicleEntry> entries);

    protected List<VehicleEntry> getEntriesInTheSession(List<VehicleEntry> entries, SessionOfTheDay session) {
        return entries.stream().
                filter(entry -> entry.entryTime().compareTo(session.startTime) >= 0
                        && entry.entryTime().compareTo(session.endTime) < 0)
                .collect(Collectors.toList());
    }

    protected List<VehicleEntry> getEntriesInTheDirection(List<VehicleEntry> entries, Direction direction) {
        return entries.stream().
                filter(entry -> entry.getDirection() == direction)
                .collect(Collectors.toList());
    }
}
