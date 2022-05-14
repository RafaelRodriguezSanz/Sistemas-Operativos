package com.ucudal.tarea1.CommandExecutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


/**
* CommandExecutor is a class to execute commands in shell
* 
* Please see the {@link com.ucudal.tarea1.OS} to see the commands that use this class
* @author Rafael Rodriguez
* @since 1.0
* @version %I%, %G%
*/
public final class CommandExecutor {
    private ProcessBuilder processBuilder;
    private String output;
    private int errorCode;
    private static String password;
    private static String username;

    
    /** 
     * @param username
     * @param password
     */
    public static void setSudo(String username, String password){
        setPassword(password);
        setUsername(username);
    }
    
    /** 
     * @param password
     */
    private static void setPassword(String password) {
        CommandExecutor.password = password;
    }
    
    /** 
     * @param username
     */
    private static void setUsername(String username) {
        CommandExecutor.username = username;
    }
    
    /** 
     * @param processBuilder
     */
    public void setProcessBuilder(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }
    public CommandExecutor() {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory( new File(System.getenv("HOME")));
        setProcessBuilder(builder);
    }
    
    /** 
     * @param path
     */
    public void setDirectory(File path) {
        this.processBuilder.directory(path);
    }
    
    /** 
     * @return File
     */
    public File getDirectory() {
        return processBuilder.directory();
    }
    
    /** 
     * @param errorCode
     */
    private void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    
    /** 
     * @return int
     */
    public int getErrorCode() {
        return errorCode;
    }
    
    /** 
     * @param output
     */
    private void setOutput(String output) {
        this.output = output;
    }
    
    /** 
     * @return String
     */
    public String getOutput() {
        return output;
    }

    
    /** 
     * @return boolean
     */
    public boolean execute() {
        setOutput("");
        try {
            Process process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
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

    
    /** 
     * @param command
     */
    public void addCommand(String command) {
        if (processBuilder.command().size()==0) {
            processBuilder.command(new String[] { "/bin/bash", "-c","echo "+CommandExecutor.password+"| sudo -S -u "+CommandExecutor.username+" -s "+command});
        }
        else{
            processBuilder.command().add(command);
        }
    }

    
    /** 
     * @param scriptName
     */
    public void addScript(String scriptName) {
        this.addScript(scriptName,"");
    }
    
    /** 
     * @param scriptName
     * @param arguments
     */
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
        this.addCommand("sh "+scriptName+ arguments);
        this.setDirectory(new File(scriptPath));
        this.showCommands();
    }
	
    
    /** 
     * @param commands
     */
    public void addCommand(String[] commands) {
        for (String command : commands) {
            addCommand(command);
        }
    }

    
    /** 
     * @return List<String>
     */
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
    
    /** 
     * @return String
     */
    public static String getPassword() {
        return password;
    }
}
