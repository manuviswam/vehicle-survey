package com.manuviswam;

import com.manuviswam.processors.IDataProcessor;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private Reader reader;
    private Map<String, IDataProcessor> processors = new HashMap<>();

    public Application(Reader reader, Map<String, IDataProcessor> processors) {
        this.reader = reader;
        this.processors = processors;
    }

    public void run(){

    }

    public static void main(String[] args) {

    }
}
