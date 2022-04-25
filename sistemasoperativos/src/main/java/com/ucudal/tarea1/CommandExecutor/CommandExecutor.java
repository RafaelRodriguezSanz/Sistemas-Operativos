package com.ucudal.tarea1.CommandExecutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class CommandExecutor {
    private ProcessBuilder processBuilder;
    private String output;
    private int errorCode;

    public CommandExecutor() {
        this.processBuilder = new ProcessBuilder();
        this.processBuilder.directory( new File(System.getenv("HOME")));
    }
    public void setDirectory(File path) {
        this.processBuilder.directory(path);
    }
    public File getDirectory() {
        return processBuilder.directory();
    }
    private void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }
    private void setOutput(String output) {
        this.output = output;
    }
    public String getOutput() {
        return output;
    }

    public boolean execute() {
        setOutput("");
        try {
            Process process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                setOutput(getOutput()+'\n'+line);
            }
            setErrorCode(process.waitFor());
            return true;
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            return false;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
        
    }

    public void addCommand(String command) {
        processBuilder.command(new String[] { "/bin/bash", "-c",command});
    // -- Linux --

	// Run a shell command
	//processBuilder.command("bash", "-c", "ls /home/mkyong/");

	// Run a shell script
	//processBuilder.command("path/to/hello.sh");

	// -- Windows --

	// Run a command
	//processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

	// Run a bat file
	//processBuilder.command("C:\\Users\\mkyong\\hello.bat");
    
    }

    //Esto funciona con .sh y con .bat
    public void addScript(String scriptName,String[] arguments) {
        String scriptPath = (new File(System.getProperty("user.dir"))).getPath()+File.separator+
                            "src"+File.separator+
                            "main"+File.separator+
                            "java"+File.separator+
                            "com"+File.separator+
                            "ucudal"+File.separator+
                            "tarea1"+File.separator+
                            "Scripts";
        this.setDirectory(new File(scriptPath));
        if (scriptName.endsWith(".bat") || scriptName.endsWith(".cmd")) {
            processBuilder.command(new String[] { "cmd.exe", "/c", scriptName});
        }
        processBuilder.command(new String[] { "sudo","sh","./"+scriptName,"-u",arguments[0],arguments[1]});
    }
	
    public void addCommand(String[] commands) {
        for (String command : commands) {
            addCommand(new String[] { "/bin/bash", "-c",command});
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

    public void printOutput(){
        System.out.println(this.output);
    }
}
