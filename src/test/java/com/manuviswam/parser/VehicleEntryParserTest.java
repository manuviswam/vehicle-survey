package com.manuviswam.parser;

import com.manuviswam.model.Direction;
import com.manuviswam.model.VehicleEntry;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleEntryParserTest {

    @Test
    public void shouldCreateEntryInNorthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "A1236"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertEquals(Direction.NORTH , output.get(0).getDirection());
    }

    @Test
    public void shouldCreateMultipleEntriesInNorthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "A1236", "A1237", "A1238"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertEquals(2, output.size());
    }

    @Test
    public void shouldCreateEntryInSouthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "B1236", "A1345", "B1356"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertEquals(Direction.SOUTH , output.get(0).getDirection());
    }

    @Test
    public void shouldCreateMultipleEntriesInSouthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "B1236", "A1345", "B1356", "A1357", "B1358", "A1359", "B1360"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertEquals(2, output.size());
    }


    @Test
    public void shouldCreateMultipleEntriesInBothDirections() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "A1236", "A1345", "B1356", "A1357", "B1358", "A1359", "A1360"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertEquals(3, output.size());
        assertEquals(Direction.NORTH, output.get(0).getDirection());
        assertEquals(Direction.SOUTH, output.get(1).getDirection());
        assertEquals(Direction.NORTH, output.get(2).getDirection());
    }

    @Test
    public void shouldReturnEmptyListForInsufficientNumberOfInputsInNorthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "A1236", "A1345"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListForInsufficientNumberOfInputsInSouthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "B1236", "A1345", "B1236", "A1345", "B1236", "A1345"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListForInsufficientNumberOfInputsToFIndDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListForInvalidOrderOfInputsInSouthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "B1236", "A1345", "A1236"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListForInvalidOrderOfInputsInSouthDirection2() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A1234", "B1236", "B1345", "A1236"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListForInvalidTimeInSouthDirection() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A86400001", "B86400001", "A86400001", "B86400001"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }


    @Test
    public void shouldReturnEmptyListForInvalidTimeInNorthDirection2() throws Exception {
        List<String> input = new ArrayList<>(Arrays.asList("A86400001", "A86400001"));
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }
}