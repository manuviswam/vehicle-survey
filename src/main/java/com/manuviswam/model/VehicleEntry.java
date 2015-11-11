package com.manuviswam.model;

import com.manuviswam.constants.Time;
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
    public int speedInKMPH(){
        return -1;
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
