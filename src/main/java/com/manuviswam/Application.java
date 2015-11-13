package com.manuviswam;

import com.manuviswam.constants.App;
import com.manuviswam.io.InputReader;
import com.manuviswam.model.VehicleEntry;
import com.manuviswam.parser.VehicleEntryParser;
import com.manuviswam.processors.IDataProcessor;
import com.manuviswam.processors.SpeedDistributionProcessor;
import com.manuviswam.processors.VehicleCountProcessor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
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
        Reader reader = null;
        if (args.length == 0) {
            reader = new InputStreamReader(Application.class.getClassLoader().getResourceAsStream(App.DEFAULT_INPUT_FILE_PATH));
        }else {
            try {
                reader = new FileReader(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("Error reading file : " + args[0] +"\nExiting application...");
            }
        }

        int[] intervalsInMinutes = {720, 60, 30, 20, 15};
        HashMap<String, IDataProcessor> processors = createProcessorsWithIntervals(intervalsInMinutes);

        new Application(reader, processors).run();
    }

    private static HashMap<String, IDataProcessor> createProcessorsWithIntervals(int[] intervalsInMinutes) {
        HashMap<String, IDataProcessor> processors = new HashMap<>();
        for (int interval : intervalsInMinutes){
            String key = "Vehicle counts in " + interval + " minute intervals";
            processors.put(key, new VehicleCountProcessor(interval));
            key = "Speed distribution in " + interval + " minute intervals";
            processors.put(key, new SpeedDistributionProcessor(interval));
            key = "Average distance of vehicles in " + interval + " minute intervals";
            processors.put(key, new SpeedDistributionProcessor(interval));
        }
        return processors;
    }
}
