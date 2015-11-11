package com.manuviswam.processors;

import com.manuviswam.model.Direction;
import com.manuviswam.model.VehicleEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleCountProcessorTest {
    private List<VehicleEntry> entries = new ArrayList<>();
    private String expectedOutput =
            "Session 00:00:00 to 01:00:00 | Day 0 Count=2 | Day 1 Count=3 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 01:00:00 to 02:00:00 | Day 0 Count=1 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 02:00:00 to 03:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 03:00:00 to 04:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 04:00:00 to 05:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 05:00:00 to 06:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 06:00:00 to 07:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 07:00:00 to 08:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 08:00:00 to 09:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 09:00:00 to 10:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 10:00:00 to 11:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 11:00:00 to 12:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 12:00:00 to 13:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 13:00:00 to 14:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 14:00:00 to 15:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 15:00:00 to 16:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 16:00:00 to 17:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 17:00:00 to 18:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 18:00:00 to 19:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 19:00:00 to 20:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 20:00:00 to 21:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 21:00:00 to 22:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 22:00:00 to 23:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "Session 23:00:00 to 00:00:00 | Day 0 Count=1 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
            "\nSession 00:00:00 to 01:00:00 is peak session with a vehicle count of 5 across 5 days";

    @Before
    public void setUp() throws Exception {
        entries.add(new VehicleEntry(86328771,86328899, Direction.SOUTH,0));
        entries.add(new VehicleEntry(328771,328899, Direction.SOUTH,0));
        entries.add(new VehicleEntry(6328771,6328899, Direction.SOUTH,0));
        entries.add(new VehicleEntry(28771,28899, Direction.SOUTH,0));
        entries.add(new VehicleEntry(582668,582787, Direction.SOUTH,1));
        entries.add(new VehicleEntry(82668,82787, Direction.SOUTH,1));
        entries.add(new VehicleEntry(2668,2787, Direction.SOUTH,1));
    }

    @Test
    public void shouldGiveSessionviceOutputForHourlyCount() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(60);
        String output = processor.process(entries);

        assertEquals(expectedOutput, output);
    }

    @Test
    public void shouldGivePeakSessionInformationInOutput() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(60);
        String output = processor.process(entries);

        assertTrue(output.contains("Session 00:00:00 to 01:00:00 is peak session with a vehicle count of 5 across 5 days"));
    }

    @Test
    public void shouldNotGiveOutputIfSessionIntervalIsNotEvenlyDistributed() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(25);
        String output = processor.process(entries);

        assertEquals("", output);
    }
}