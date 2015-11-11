package com.manuviswam.model;

import com.manuviswam.constants.Time;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class VehicleEntryTest {
    private static final double DELTA = 1e-15;

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


    @Test
    public void shouldReturnSpeedOfVehicle() throws Exception {
        VehicleEntry output = new VehicleEntry(1000, 2000, Direction.SOUTH);

        assertEquals(9d, output.speedInKMPH(), DELTA);
    }

    @Test
    public void shouldReturnZeroForSpeedIfDataIsInvalid() throws Exception {
        VehicleEntry output = new VehicleEntry(1000, 500, Direction.SOUTH);

        assertEquals(0d, output.speedInKMPH(), DELTA);
    }


    @Test
    public void shouldReturnTimeOfEntry() throws Exception {
        VehicleEntry output = new VehicleEntry(1000, 2000, Direction.SOUTH);
        Date expected = new Date(1500);
        assertEquals(expected, output.entryTime());
    }
}