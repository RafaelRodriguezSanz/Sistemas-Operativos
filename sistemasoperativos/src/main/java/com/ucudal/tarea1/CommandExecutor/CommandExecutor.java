package com.ucudal.tarea1.CommandExecutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public final class CommandExecutor {
    private static CommandExecutor instance;
    private static String DEFAULT_PATH = "/usr/local/";

    private CommandExecutor() {
        // Singleton Creation
    }

    public static CommandExecutor getInstance() {
        if (instance == null) {
            instance = new CommandExecutor();
        }
        return instance;
    }

    private static void execute(String command) {
        execute(command, DEFAULT_PATH);
    }

    private static void execute(String command, String folder) {
        String[] commands = { "CMD", "/C", command };
        ProcessBuilder probuilder = new ProcessBuilder(commands);
        probuilder.directory(new File(folder));
        try {
            Process process = probuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.printf("Output of running %s is:\n", Arrays.toString(commands));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitValue = process.waitFor();
            System.out.println("\n\nExit Value is " + exitValue);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}