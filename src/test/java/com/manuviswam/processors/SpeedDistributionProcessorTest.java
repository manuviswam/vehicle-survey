package com.manuviswam.processors;

import com.manuviswam.model.Direction;
import com.manuviswam.model.VehicleEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpeedDistributionProcessorTest {
    private List<VehicleEntry> entries = new ArrayList<>();
    private String expectedOutput =
                    "Session 00:00:00 to 06:00:00 | Average speed = 72.97137605042018\n" +
                    "Session 06:00:00 to 12:00:00 | Average speed = 0.0\n" +
                    "Session 12:00:00 to 18:00:00 | Average speed = 0.0\n" +
                    "Session 18:00:00 to 00:00:00 | Average speed = 70.3125\n";

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
    public void shouldGiveAverageSpeedForEachSession() throws Exception {
        String output = new SpeedDistributionProcessor(360).process(entries);

        assertEquals(expectedOutput, output);
    }

    @Test
    public void shouldGiveAverageSpeedAsZeroForEachSessionWithNoTraffic() throws Exception {
        String output = new SpeedDistributionProcessor(360).process(entries);

        assertTrue(output.contains("Session 06:00:00 to 12:00:00 | Average speed = 0.0"));
        assertTrue(output.contains("Session 12:00:00 to 18:00:00 | Average speed = 0.0"));
    }

    @Test
    public void shouldNotGiveAnyOutputIfSessionCannotbeDistributedEvenly() throws Exception {
        String output = new SpeedDistributionProcessor(25).process(entries);

        assertEquals("", output);
    }
}