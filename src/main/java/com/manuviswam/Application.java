package com.manuviswam;

import com.manuviswam.io.InputReader;
import com.manuviswam.model.VehicleEntry;
import com.manuviswam.parser.VehicleEntryParser;
import com.manuviswam.processors.IDataProcessor;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private Reader reader;
    private Map<String, IDataProcessor> processors = new HashMap<>();

    public Application(Reader reader, Map<String, IDataProcessor> processors) {
        this.reader = reader;
        this.processors = processors;
    }

    public void run(){
        List<String> allInputLines = new InputReader(reader).getAllInputLines();
        if (allInputLines.size() == 0){
            System.out.println("Error reading inputs. Exiting application...");
            return;
        }

        List<VehicleEntry> allEntries = new VehicleEntryParser().parse(allInputLines);
        if (allEntries.size() == 0){
            System.out.println("Error parsing inputs. Exiting application...");
            return;
        }

        processors.forEach( (key, processor) -> {
            System.out.println(key);
            System.out.println(processor.process(allEntries));
        });
    }


    public static void main(String[] args) {

    }
}
