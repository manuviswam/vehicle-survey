package com.manuviswam;

import com.manuviswam.model.VehicleEntry;
import com.manuviswam.processors.IDataProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationTest {

    private HashMap<String, IDataProcessor> processors;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        processors = new HashMap<>();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void shouldNotCallProcessorIfInputHasErrors() throws Exception {
        Reader reader = new StringReader("Invalid input");
        FakeProcessor processor = new FakeProcessor("");
        processors.put("Processor 1", processor);

        new Application(reader, processors).run();

        assertFalse(processor.isInvoked());
    }

    @Test
    public void shouldCallAllProcessorsIfInputIsValid() throws Exception {
        Reader reader = new StringReader("A1234\nA1345");
        FakeProcessor processor = new FakeProcessor("");
        FakeProcessor processor2 = new FakeProcessor("");
        processors.put("Processor 1", processor);
        processors.put("Processor 2", processor2);

        new Application(reader, processors).run();

        assertTrue(processor.isInvoked());
        assertTrue(processor2.isInvoked());
    }

    @Test
    public void shouldDisplayKeysOfAllProcessorsInConsole() throws Exception {
        Reader reader = new StringReader("A1234\nA1345");
        FakeProcessor processor = new FakeProcessor("");
        FakeProcessor processor2 = new FakeProcessor("");
        processors.put("Processor 1", processor);
        processors.put("Processor 2", processor2);

        new Application(reader, processors).run();

        assertTrue(outContent.toString().contains("Processor 1"));
        assertTrue(outContent.toString().contains("Processor 2"));
    }

    @Test
    public void shouldDisplayResultsOfAllProcessorsInConsole() throws Exception {
        Reader reader = new StringReader("A1234\nA1345");
        FakeProcessor processor = new FakeProcessor("Output of processor 1");
        FakeProcessor processor2 = new FakeProcessor("Output of processor 1");
        processors.put("Processor 1", processor);
        processors.put("Processor 2", processor2);

        new Application(reader, processors).run();

        assertTrue(outContent.toString().contains("Output of processor 1"));
        assertTrue(outContent.toString().contains("Output of processor 1"));
    }

    class FakeProcessor implements IDataProcessor {
        private boolean invoked;
        private String returnValue;

        public FakeProcessor(String returnValue) {
            invoked = false;
            this.returnValue = returnValue;
        }

        @Override
        public String process(List<VehicleEntry> entries) {
            invoked =true;
            return returnValue;
        }

        public boolean isInvoked(){
            return invoked;
        }
    }
}

