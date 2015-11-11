package com.manuviswam.parser;

import com.manuviswam.constants.App;
import com.manuviswam.helpers.TimeParser;
import com.manuviswam.model.Direction;
import com.manuviswam.model.VehicleEntryCreationException;
import com.manuviswam.model.VehicleEntry;

import java.util.ArrayList;
import java.util.List;

public class VehicleEntryParser {

    public static final int ENTRIES_FOR_NORTH_DIRECTION = 2;
    public static final int ENTRIES_FOR_SOUTH_DIRECTION = 4;
    public static final int MINIMUM_NUMBER_OF_ENTRIES_NEEDED = 2;

    public List<VehicleEntry> parse(List<String> input){
        List<VehicleEntry> emptyList = new ArrayList<>();
        List<VehicleEntry> entries = new ArrayList<>();

        while (input.size() > 0){
            if (isInsufficientEntries(input, MINIMUM_NUMBER_OF_ENTRIES_NEEDED)){
                return emptyList;
            }

            Direction direction = findDirection(input.get(0),input.get(1));
            int numberOfEntriesNeeded = (direction == Direction.SOUTH ? ENTRIES_FOR_SOUTH_DIRECTION : ENTRIES_FOR_NORTH_DIRECTION);
            if(isInsufficientEntries(input,numberOfEntriesNeeded))
                return emptyList;
            try {
                if (direction == Direction.SOUTH)
                    input = tryAddSouthWardsEntry(input, entries);
                else
                    input = tryAddNorthWardsEntry(input, entries);
            } catch (VehicleEntryCreationException e) {
                System.out.println("Error while creating entry");
                return emptyList;
            }
        }
        return entries;
    }

    private boolean isInsufficientEntries(List<String> input, int numberOfEntriesNeeded) {
        return input.size() < numberOfEntriesNeeded;
    }

    private List<String> tryAddSouthWardsEntry(List<String> input, List<VehicleEntry> entries) throws VehicleEntryCreationException {
        String frontAxleSensor1Entry = input.get(0);
        String frontAxleSensor2Entry = input.get(1);
        String rearAxleSensor1Entry = input.get(2);
        String rearAxleSensor2Entry = input.get(3);
        if(!isOrderOfEntriesValid(frontAxleSensor1Entry,frontAxleSensor2Entry,rearAxleSensor1Entry,rearAxleSensor2Entry))
            throw new VehicleEntryCreationException("Invalid order of entries : " + frontAxleSensor1Entry + "," + frontAxleSensor2Entry + "," + rearAxleSensor1Entry + "," + rearAxleSensor2Entry);

        int frontAxleSensor1Time = TimeParser.parseMilliSecondFromInput(frontAxleSensor1Entry);
        int frontAxleSensor2Time = TimeParser.parseMilliSecondFromInput(frontAxleSensor2Entry);
        int rearAxleSensor1Time = TimeParser.parseMilliSecondFromInput(rearAxleSensor1Entry);
        int rearAxleSensor2Time = TimeParser.parseMilliSecondFromInput(rearAxleSensor2Entry);

        int frontAxleTime = (frontAxleSensor1Time + frontAxleSensor2Time)/2;
        int rearAxleTime = (rearAxleSensor1Time + rearAxleSensor2Time)/2;

        VehicleEntry entry = new VehicleEntry(frontAxleTime, rearAxleTime, Direction.SOUTH);
        if (!entry.isValid())
            throw new VehicleEntryCreationException("Invalid record : " + entry);
        entries.add(entry);
        return input.subList(4,input.size());
    }

    private boolean isOrderOfEntriesValid(String frontAxleSensor1Entry, String frontAxleSensor2Entry, String rearAxleSensor1Entry, String rearAxleSensor2Entry) {
        return frontAxleSensor1Entry.startsWith(App.SENSOR1_PREFIX)
                && frontAxleSensor2Entry.startsWith(App.SENSOR2_PREFIX)
                && rearAxleSensor1Entry.startsWith(App.SENSOR1_PREFIX)
                && rearAxleSensor2Entry.startsWith(App.SENSOR2_PREFIX);
    }

    private List<String> tryAddNorthWardsEntry(List<String> input, List<VehicleEntry> entries) throws VehicleEntryCreationException {
        String frontAxleEntry = input.get(0);
        String rearAxleEntry = input.get(1);

        int frontAxleTime = TimeParser.parseMilliSecondFromInput(frontAxleEntry);
        int rearAxleTime = TimeParser.parseMilliSecondFromInput(rearAxleEntry);

        VehicleEntry entry = new VehicleEntry(frontAxleTime, rearAxleTime, Direction.NORTH);
        if (!entry.isValid())
            throw new VehicleEntryCreationException("Invalid record : " + entry);
        entries.add(entry);
        return input.subList(2,input.size());
    }

    private Direction findDirection(String firstEntry, String secondEntry) {
        if (firstEntry.startsWith(App.SENSOR1_PREFIX) && secondEntry.startsWith(App.SENSOR1_PREFIX))
            return Direction.NORTH;
        return Direction.SOUTH;
    }
}
