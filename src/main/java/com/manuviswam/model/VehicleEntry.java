package com.manuviswam.model;

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
}
