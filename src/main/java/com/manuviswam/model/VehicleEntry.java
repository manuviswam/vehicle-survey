package com.manuviswam.model;

public class VehicleEntry {
    private int frontAxleTime;
    private int rearAxleTime;

    public Direction direction;

    public VehicleEntry(int frontAxleTime, int rearAxleTime, Direction direction) {
        this.frontAxleTime = frontAxleTime;
        this.rearAxleTime = rearAxleTime;
        this.direction = direction;
    }
}
