package com.manuviswam;

import com.manuviswam.model.VehicleEntry;
import com.manuviswam.processors.IDataProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationTest {

    private HashMap<String, IDataProcessor> processors;

    @Before
    public void setUp() throws Exception {
        processors = new HashMap<>();
    }

    @Test
    public void shouldNotCallProcessorIfInputHasErrors() throws Exception {
        Reader reader = new StringReader("Invalid input");
        FakeProcessor processor = new FakeProcessor("");
        processors.put("Processor 1", processor);

        new Application(reader, processors).run();

        assertFalse(processor.isInvoked());
    }

    class FakeProcessor implements IDataProcessor {
        private boolean invoked = false;
        private String returnValue;

        public FakeProcessor(String returnValue) {
            this.returnValue = returnValue;
        }

        @Override
        public String process(List<VehicleEntry> entries) {
            return returnValue;
        }

        public boolean isInvoked(){
            return invoked;
        }
    }
}

