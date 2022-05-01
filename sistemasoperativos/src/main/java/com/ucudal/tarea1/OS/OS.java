package com.ucudal.tarea1.OS;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;

public class OS {

    public static boolean checkSudo(String user, String password) {
        String command = "if((case \"" + user + "\" in *$(grep '^sudo:.*$' /etc/group | cut -d: -f4)*) true;;*) false;;esac) && (if ( echo " + password + " | su -c true " + user + " ); then true; else false; fi));then echo \"true\"; else echo \"false\"; fi";
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(new String[] { "/bin/bash", "-c",command});
        pb.directory(new File("/"));
        // starting the process
        Process process;
        String s = "";
        String output = "";
        try {
            process = pb.start();
            // for reading the output from stream
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((s = stdInput.readLine()) != null) {
                output+='\n'+s; 
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output.trim().contains("true");
    }

    // Create a group if it doesn´t not exist
    public static boolean groupExist(String groupName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("if sudo -S getent group \"" + groupName + "\" &>/dev/null; then echo 'true'; else echo 'false'; fi");
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput().trim());
    }

    // Create a group if it doesn´t not exist
    public static boolean createGroup(String groupName) {
        if (!OS.groupExist(groupName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("sudo -S  groupadd " + groupName);
            cmd.execute();
            return cmd.getOutput().isEmpty();
        } else {
            return false;
        }
    }

    // Add a new user (if does not exist), with userName and privilegies
    // Return false if it already exist or can´t be created
    public static boolean createUser(String userName, String privilegies) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S  useradd " + userName);
        cmd.execute();
        cmd.showCommands();
        return cmd.getOutput().isEmpty();
    }

    // Returns the userName user info
    // Returns null if user was not found or info could not be get
    public static String getUserInfo(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userName);
        return cmd.getOutput();
    }

    // Returns all users info
    // Returns null if users could not found
    public static String[] getUsers() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd");
        cmd.execute();
        return cmd.getOutput().split("\n");
    }

    // Returns groups and their users
    // Returns null if no group could not found
    public static String[] getGroups() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent group");
        cmd.execute();
        return cmd.getOutput().split("\n");
    }

    public static boolean userExists(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("if sudo -S getent passwd \"" + userName + "\" &>/dev/null; then echo 'true'; else echo 'false'; fi");
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput().trim());
    }

    public static boolean userExists(int userID) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("if sudo -S getent passwd " + userID + " &>/dev/null; then echo 'true'; else echo 'false'; fi");
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput().trim());
    }

    public static String userEncriptedPassword(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userName + " |cut -d: -f2 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String userID(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userName + " |cut -d: -f3 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String userName(int userID) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userID + " |cut -d: -f1 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static int userGroupID(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userName + " |cut -d: -f4 ");
        cmd.execute();
        return Integer.parseInt(cmd.getOutput().trim());
    }

    public static String userDescription(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userName + " |cut -d: -f5 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String userHomeFolder(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userName + " |cut -d: -f6 ");
        return cmd.getOutput();
    }

    public static String userShell(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S getent passwd " + userName + " |cut -d: -f7 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String currentUser() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S whoami");
        cmd.execute();
        return cmd.getOutput().trim();
    }

    public static boolean backupUser(String userName) {
        return backupUser(userName, false);
    }

    public static boolean backupUser(int userName) {
        return backupUser(userName, false);
    }

    // No esta hecho esto todavia, es solo el esqueleto de como podria ser
    public static boolean backupUser(String userName, boolean rewrite) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addScript("copiaSeguridad.sh", userName+" "+(rewrite ? "-r" : ""));
        cmd.execute();
        cmd.showCommands();
        return cmd.getOutput().contains("true");
    }

    public static boolean backupUser(int userID, boolean rewrite) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addScript("copiaSeguridad.sh", userID+" "+(rewrite ? "-r" : ""));
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput());
    }

    public static boolean cleanAllBackups() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S  rm -r ./backups");
        cmd.setDirectory(new File("/home"));
        cmd.execute();
        return cmd.getOutput().isEmpty();
    }

    public static boolean cleanUserBackups(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo -S  rm -r ./backups/" + userName);
        cmd.setDirectory(new File("/home"));
        cmd.execute();
        return cmd.getOutput().isEmpty();
    }

    public static boolean removeGroup(String groupName) {
        if (OS.groupExist(groupName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("sudo -S  groupdel " + groupName);
            cmd.execute();
            return cmd.getOutput().trim().isEmpty();
        } else {
            return false;
        }
    }

    public static boolean removeUser(String userName) {
        if (OS.userExists(userName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("sudo -S userdel " + userName + "&& rm -r /home/" + userName);
            cmd.execute();
            cmd.showCommands();
            return cmd.getOutput().trim().isEmpty();
        } else {
            return false;
        }
    }

    public static void addChrontab (){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addScript("createCrontab.sh");
        cmd.execute();
        cmd.showCommands();
    }
}
