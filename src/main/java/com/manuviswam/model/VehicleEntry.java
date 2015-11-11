package com.manuviswam.model;

public class VehicleEntry {
    private int firstAxleTime;
    private int secondAxleTime;

    public Direction direction;

    public VehicleEntry(int firstAxleTime, int secondAxleTime, Direction direction) {
        this.firstAxleTime = firstAxleTime;
        this.secondAxleTime = secondAxleTime;
        this.direction = direction;
    }
}
