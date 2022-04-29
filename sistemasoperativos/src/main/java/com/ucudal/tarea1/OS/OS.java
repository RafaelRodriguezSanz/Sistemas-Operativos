package com.ucudal.tarea1.OS;

import java.io.File;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;

public class OS {

    public static boolean checkSudo(String user, String password) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(
                "if ( echo " + password + " | su -c true " + user + ") ; then echo \"true\"; else echo \"false\"; fi");
        cmd.execute();
        return cmd.getOutput().trim().contains("true");
    }

    // Clean all groups and users in the OS except root
    public static boolean cleanAllUsers() {
        return true;
    }

    // Clean all groups and users in the OS except root
    public static boolean cleanAllGropus() {
        return true;
    }

    // Create a group if it doesn´t not exist
    public static boolean groupExist(String groupName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("if getent group " + groupName + " &>/dev/null; then echo 'true'; else echo 'false'; fi");
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput().trim());
    }

    // Create a group if it doesn´t not exist
    public static boolean createGroup(String groupName) {
        if (!OS.groupExist(groupName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("echo admin |sudo -S groupadd " + groupName);
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
        cmd.addCommand("echo admin |sudo -S useradd " + userName);
        cmd.execute();
        return cmd.getOutput().isEmpty();
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
        cmd.addCommand("getent passwd " + userName);
        return cmd.getOutput();
    }

    // Returns all users info
    // Returns null if users could not found
    public static String[] getUsers() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd");
        cmd.execute();
        return cmd.getOutput().split("\n");
    }

    // Returns groups and their users
    // Returns null if no group could not found
    public static String[] getGroups() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent group");
        cmd.execute();
        return cmd.getOutput().split("\n");
    }

    public static boolean userExists(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("if getent passwd \"" + userName + "\" &>/dev/null; then echo 'true'; else echo 'false'; fi");
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput().trim());
    }

    public static boolean userExists(int userID) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("if getent passwd " + userID + " &>/dev/null; then echo 'true'; else echo 'false'; fi");
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput().trim());
    }

    public static String userEncriptedPassword(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f2 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String userID(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f3 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String userName(int userID) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userID + " |cut -d: -f1 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static int userGroupID(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f4 ");
        cmd.execute();
        return Integer.parseInt(cmd.getOutput().trim());
    }

    public static String userDescription(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f5 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String userHomeFolder(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f6 ");
        return cmd.getOutput();
    }

    public static String userShell(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f7 ");
        cmd.execute();
        return cmd.getOutput();
    }

    public static String currentUser() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("whoami");
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
        cmd.addScript("copiaSeguridad.sh", new String[] { userName, rewrite ? "-r" : "" });
        cmd.execute();
        return true;
    }

    public static boolean backupUser(int userID, boolean rewrite) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addScript("copiaSeguridad.sh", new String[] { userName(userID), rewrite ? "-r" : "" });
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput());
    }

    public static boolean cleanAllBackups() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo rm -r ./backups");
        cmd.setDirectory(new File("/home"));
        cmd.execute();
        return cmd.getOutput().isEmpty();
    }

    public static boolean cleanUserBackups(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo rm -r ./backups/" + userName);
        cmd.setDirectory(new File("/home"));
        cmd.execute();
        return cmd.getOutput().isEmpty();
    }

    public static boolean removeGroup(String groupName) {
        if (OS.groupExist(groupName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("echo admin |sudo -S groupdel " + groupName);
            cmd.execute();
            return cmd.getOutput().trim().isEmpty();
        } else {
            return false;
        }
    }

    public static boolean removeUser(String userName) {
        if (OS.groupExist(userName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("echo admin |sudo -S userdel " + userName + "&& rm -r /home/" + userName);
            cmd.execute();
            return cmd.getOutput().trim().isEmpty();
        } else {
            return false;
        }
    }
}
