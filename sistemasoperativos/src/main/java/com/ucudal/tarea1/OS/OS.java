package com.ucudal.tarea1.OS;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;

public class OS {

    // Clean all groups and users in the OS except root
    public static boolean cleanAll() {
        return true;
    }

    // Create a group if it doesn´t not exist
    public static boolean createGroup(String groupName) {
        return true;
    }

    // Add a new user (if does not exist), with userName and privilegies
    // Return false if it already exist or can´t be created
    public static boolean createUser(String userName, String privilegies) {
        return true;
    }

    // It create a backup of the userName user data
    public static boolean backup(String userName) {
        return true;
    }

    // Add privileges to userName user
    public static boolean addPrivileges(String userName, String privileges) {
        return true;
    }

    // Removes privileges to userName user
    public static boolean removePrivileges(String userName, String privileges) {
        return true;
    }

    // Returns the userName user info
    // Returns null if user was not found or info could not be get
    public static String getUserInfo(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName);
        return cmd.getOutput();
    }

    // Returns all users info
    // Returns null if users could not found
    public static String getUsers() {
        return "";
    }

    // Returns groups and their users
    // Returns null if no group could not found
    public static String getGroups() {
        return "";
    }

    public static boolean userExists(String userName){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName+" |cut -d: -f1 ");
        return cmd.execute();
    }

    public static String userEncriptedPassword(String userName){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName+" |cut -d: -f2 ");
        cmd.execute();
        return cmd.getOutput();
    }
    public static int userID(String userName){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName+" |cut -d: -f3 ");
        cmd.execute();
        return Integer.parseInt(cmd.getOutput().trim());
    }
    public static int userGroupID(String userName){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName+" |cut -d: -f4 ");
        cmd.execute();
        return Integer.parseInt(cmd.getOutput().trim());
    }
    public static String userDescription(String userName){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName+" |cut -d: -f5 ");
        cmd.execute();
        return cmd.getOutput();
    }
    public static String userHomeFolder(String userName){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName+" |cut -d: -f6 ");
        return cmd.getOutput();
    }
    public static String userShell(String userName){
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd "+userName+" |cut -d: -f7 ");
        cmd.execute();
        return cmd.getOutput();
    }
}
