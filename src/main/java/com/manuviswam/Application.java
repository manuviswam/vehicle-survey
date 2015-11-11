package com.manuviswam;

public class Application {
    public static void main(String[] args) {
        String defaultPath = Application.class.getClassLoader().getResource(com.manuviswam.constants.App.DEFAULT_INPUT_FILE_PATH ).toString();
        String inputFilePath = "".equals(args[0]) ? com.manuviswam.constants.App.DEFAULT_INPUT_FILE_PATH : args[0];

    }
}
