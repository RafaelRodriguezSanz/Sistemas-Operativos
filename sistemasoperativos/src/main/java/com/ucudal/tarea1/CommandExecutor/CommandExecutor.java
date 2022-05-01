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
    private static String password;
    private static String username;

    public static void setSudo(String username, String password){
        setPassword(password);
        setUsername(username);
    }
    private static void setPassword(String password) {
        CommandExecutor.password = password;
    }
    private static void setUsername(String username) {
        CommandExecutor.username = username;
    }
    public void setProcessBuilder(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }
    public CommandExecutor() {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory( new File(System.getenv("HOME")));
        setProcessBuilder(builder);
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
        processBuilder.command(new String[] { "/bin/bash", "-c","echo "+CommandExecutor.password+" | su -c \"echo "+CommandExecutor.password+"| "+command + "\" "+ CommandExecutor.username});
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
    public void addScript(String scriptName) {
        this.addScript(scriptName,"");
    }
    //Esto funciona con .sh y con .bat
    public void addScript(String scriptName,String arguments) {
        String scriptPath = (new File(System.getProperty("user.dir"))).getPath()+File.separator+
                            "src"+File.separator+
                            "main"+File.separator+
                            "java"+File.separator+
                            "com"+File.separator+
                            "ucudal"+File.separator+
                            "tarea1"+File.separator+
                            "Scripts";
        this.addCommand("sudo -S sh "+scriptName);
        this.setDirectory(new File(scriptPath));
        
    }
	
    public void addCommand(String[] commands) {
        for (String command : commands) {
            addCommand(new String[] { "/bin/bash", "-c","echo "+CommandExecutor.password+" | su -c \"echo "+CommandExecutor.password+"| "+command + "\" "+ CommandExecutor.username});
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
