package com.manuviswam.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void shouldReturnMillisecondForValidInput() throws Exception {
        int output = Time.parseMilliSecondFromInput("A2134");
        assertEquals(2134,output);
    }

    @Test
    public void shouldReturnMinusOneForInValidInput() throws Exception {
        int output = Time.parseMilliSecondFromInput("A21A4");
        assertEquals(-1,output);
    }

    @Test
    public void shouldReturnMinusOneForLargeInput() throws Exception {
        int output = Time.parseMilliSecondFromInput("A123456789123456789123456789");
        assertEquals(-1,output);
    }
}