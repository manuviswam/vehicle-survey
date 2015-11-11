package com.manuviswam.model;

import com.manuviswam.constants.App;
import com.manuviswam.constants.Time;
import com.manuviswam.helpers.TimeParser;

public class VehicleEntry {
    private int frontAxleTime;
    private int rearAxleTime;

    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public VehicleEntry(int frontAxleTime, int rearAxleTime, Direction direction) {
        this.frontAxleTime = frontAxleTime;
        this.rearAxleTime = rearAxleTime;
        this.direction = direction;
    }

    public double speedInKMPH(){
        if (!isValid())
            return 0;
        double timeTakenToPassCounter = TimeParser.convertToHour(rearAxleTime - frontAxleTime);
        double speedInMeterPerSecond =  App.LENGTH_OF_VEHICLE / timeTakenToPassCounter;
        return speedInMeterPerSecond;
    }

    public boolean isValid(){
        return frontAxleTime >= 0
                && frontAxleTime < Time.MAX_MILLISECONDS_IN_A_DAY
                && rearAxleTime >= 0
                && rearAxleTime < Time.MAX_MILLISECONDS_IN_A_DAY
                && frontAxleTime < rearAxleTime;
    }

    @Override
    public String toString() {
        return "VehicleEntry{" +
                "frontAxleTime=" + frontAxleTime +
                ", rearAxleTime=" + rearAxleTime +
                ", direction=" + direction +
                '}';
    }
}
