package com.ucudal.tarea1.CommandExecutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class CommandExecutor {
    private static CommandExecutor instance;
    private static File DEFAULT_PATH;
    private ProcessBuilder processBuilder;

    private CommandExecutor() {
        DEFAULT_PATH = new File(System.getenv("HOME"));
        processBuilder = new ProcessBuilder(new String[] { "/bin/bash", "-c"});
        processBuilder.directory(DEFAULT_PATH);
    }
    public static void setDEFAULT_PATH(File path) {
        DEFAULT_PATH = path;
    }
    public static File getDEFAULT_PATH() {
        return DEFAULT_PATH;
    }
    public static CommandExecutor getInstance() {
        if (instance == null) {
            instance = new CommandExecutor();
        }
        return instance;
    }

    public int execute() {
        try {
            Process process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitValue = process.waitFor();
            return exitValue;
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            return -1;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return -2;
        }
        
    }

    public void addCommand(String command) {
        processBuilder.command(command);
    }

    public void addCommand(String[] commands) {
        for (String command : commands) {
            addCommand(command);
        }
    }

    public List<String> getCommands(){
        return processBuilder.command();
    }

    public void showCommands(){
        int i=0;
        for (String command : processBuilder.command()) {
            i++;
            System.out.println("Command "+i+": "+command);
        }
    }
}