package com.manuviswam.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class FileHelper {
    public Reader getReaderFromFile(String path){
        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file : " + path);
        }
        return null;
    }
}
