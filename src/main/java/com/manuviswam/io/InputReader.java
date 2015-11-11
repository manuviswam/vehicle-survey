package com.manuviswam.io;

import com.manuviswam.constants.AppConstants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputReader {
    private Reader input;

    public InputReader(Reader input) {
        this.input = input;
    }

    public List<String> getAllInputLines(){
        List<String> inputLines = new ArrayList<>();
        List<String> emptyList = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(input)) {

            while ((line = reader.readLine()) != null) {
                if (isValid(line)) {
                    inputLines.add(line.toUpperCase());
                } else {
                    System.out.println("Invalid input line : " + line);
                    return emptyList;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            return emptyList;
        }
        return inputLines;
    }

    private boolean isValid(String line){
        Pattern validator = Pattern.compile(AppConstants.INPUT_VALIDATION_REGEX);

        return validator.matcher(line).matches();
    }
}
