package com.manuviswam.model;

import com.manuviswam.constants.Time;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleEntryTest {

    @Test
    public void shouldReturnFalseForFrontAxleTimeLessThanZero() throws Exception {
        VehicleEntry output = new VehicleEntry(-1, 1, Direction.SOUTH);
        assertFalse(output.isValid());
    }

    @Test
    public void shouldReturnFalseForFrontAxleTimeGreaterThanMaxTime() throws Exception {
        VehicleEntry output = new VehicleEntry(Time.MAX_MILLISECONDS_IN_A_DAY, 1, Direction.SOUTH);
        assertFalse(output.isValid());
    }

    @Test
    public void shouldReturnFalseForRearAxleTimeLessThanZero() throws Exception {
        VehicleEntry output = new VehicleEntry(1, -1, Direction.SOUTH);
        assertFalse(output.isValid());
    }

    @Test
    public void shouldReturnFalseForRearAxleTimeGreaterThanMaxTime() throws Exception {
        VehicleEntry output = new VehicleEntry(1, Time.MAX_MILLISECONDS_IN_A_DAY, Direction.SOUTH);
        assertFalse(output.isValid());
    }

    @Test
    public void shouldReturnFalseForFrontAxleTimeGreaterThanRearAxleTime() throws Exception {
        VehicleEntry output = new VehicleEntry(2,1,Direction.SOUTH);
        assertFalse(output.isValid());
    }

    @Test
    public void shouldReturnTrueForValidData() throws Exception {
        VehicleEntry output = new VehicleEntry(1,2,Direction.SOUTH);
        assertTrue(output.isValid());
    }
}