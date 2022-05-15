package com.ucudal.tarea1.OS;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;

/**
 * OS is a class that executes OS Shell commands.
 * 
 * Please see the {@link com.ucudal.tarea1.CommandExecutor} to see how commands
 * are executed
 * 
 * @author Rafael Rodriguez
 * @since 1.0
 * @version %I%, %G%
 */
public class OS {

    /**
     * @param user
     * @param password
     * @return boolean
     */
    public static boolean checkSudo(String user, String password) {
        String command = "if((case \"" + user
                + "\" in *$(grep '^sudo:.*$' /etc/group | cut -d: -f4)*) true;;*) false;;esac) && (if ( echo "
                + password + " | su -c true " + user
                + " ); then true; else false; fi));then echo \"true\"; else echo \"false\"; fi";
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(new String[] { "/bin/bash", "-c", command });
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
                output += '\n' + s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Return: " + output.trim().contains("true"));
        return output.trim().contains("true");
    }

    /**
     * @param groupName
     * @return boolean
     */
    // Create a group if it doesn´t not exist
    public static boolean groupExist(String groupName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent group \"" + groupName + "\"");
        cmd.execute();
        return !cmd.getOutput().isEmpty();
    }

    /**
     * @param groupName
     * @return boolean
     */
    // Create a group if it doesn´t not exist
    public static boolean createGroup(String groupName) {
        if (!OS.groupExist(groupName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S groupadd " + groupName);
            cmd.execute();
            return cmd.getOutput().isEmpty();
        } else {
            return false;
        }
    }

    /**
     * @param userName
     * @param password
     * @param sudo
     * @return boolean
     */
    // Add a new user (if does not exist), with userName and privilegies
    // Return false if it already exist or can´t be created
    public static boolean createUser(String userName, String password, boolean sudo) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S " + "useradd -m " + userName
                + " -p $(openssl passwd -1 -stdin <<< " + password + ")");
        if (sudo) {
            cmd.addCommand("usermod -aG sudo " + userName);
        }
        cmd.execute();
        cmd.showCommands();
        return cmd.getOutput().isEmpty();
    }

    public static boolean modifyPrivilegie(String privilegies) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S  chmod " + privilegies);
        cmd.execute();

        return cmd.getOutput().isEmpty();
    }

    /**
     * @param userName
     * @param group
     * @return boolean
     */
    public static boolean addUserToGroup(String userName, String group) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S usermod -a -G " + group + " " + userName);
        cmd.showCommands();
        cmd.execute();

        return cmd.getOutput().isEmpty();
    }

    /**
     * @param userName
     * @param group
     * @return boolean
     */
    public static boolean removeUserFromGroup(String userName, String group) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S  gpasswd -d " + userName + " " + group);
        cmd.execute();
        cmd.showCommands();
        return cmd.getOutput().contains("Removing user " + userName + " from group " + group);
    }

    /**
     * @param userName
     * @return String
     */
    // Returns the userName user info
    // Returns null if user was not found or info could not be get
    public static String getUserInfo(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("sudo getent passwd " + userName);
        return cmd.getOutput();
    }

    /**
     * @return String[]
     */
    // Returns all users info
    // Returns null if users could not found
    public static String[] getUsers() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd");
        cmd.execute();
        cmd.showCommands();
        return cmd.getOutput().split("\n");
    }

    /**
     * @return String[]
     */
    // Returns groups and their users
    // Returns null if no group could not found
    public static String[] getGroups() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S getent group");
        cmd.execute();
        return cmd.getOutput().split("\n");
    }

    /**
     * @param userName
     * @return boolean
     */
    public static boolean userExists(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd \"" + userName + "\"");
        cmd.execute();

        return !cmd.getOutput().isEmpty();
    }

    /**
     * @param userID
     * @return boolean
     */
    public static boolean userExists(int userID) {
        return userExists(((Integer) (userID)).toString());
    }

    /**
     * @param userName
     * @return String
     */
    public static String userID(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f3 ");
        cmd.execute();
        return cmd.getOutput();
    }

    /**
     * @param userID
     * @return String
     */
    public static String userName(int userID) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userID + " |cut -d: -f1 ");
        cmd.execute();
        return cmd.getOutput();
    }

    /**
     * @param userName
     * @return int
     */
    public static int userGroupID(String groupName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + groupName + " |cut -d: -f4 ");
        cmd.execute();
        return Integer.parseInt(cmd.getOutput().trim());
    }

    /**
     * @param userName
     * @return String
     */
    public static String userDescription(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f5 ");
        cmd.execute();
        return cmd.getOutput();
    }

    /**
     * @param userName
     * @return String
     */
    public static String userHomeFolder(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f6 ");
        return cmd.getOutput();
    }

    /**
     * @param userName
     * @return String
     */
    public static String userShell(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("getent passwd " + userName + " |cut -d: -f7 ");
        cmd.execute();
        return cmd.getOutput();
    }

    /**
     * @return String
     */
    public static String currentUser() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("whoami");
        cmd.execute();
        return cmd.getOutput().trim();
    }

    /**
     * @param userName
     * @return boolean
     */
    public static boolean backupUser(String userName) {
        return backupUser(userName, false);
    }

    /**
     * @param userName
     * @return boolean
     */
    public static boolean backupUser(int userName) {
        return backupUser(userName, false);
    }

    /**
     * @param userName
     * @param rewrite
     * @return boolean
     */
    // No esta hecho esto todavia, es solo el esqueleto de como podria ser
    public static boolean backupUser(String userName, boolean rewrite) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addScript("copiaSeguridad.sh", "-u " + userName + " " + (rewrite ? "-r" : ""));
        cmd.execute();

        return cmd.getOutput().contains("true");
    }

    /**
     * @param userID
     * @param rewrite
     * @return boolean
     */
    public static boolean backupUser(int userID, boolean rewrite) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addScript("copiaSeguridad.sh", userID + " " + (rewrite ? "-r" : ""));
        cmd.execute();
        return Boolean.parseBoolean(cmd.getOutput());
    }

    /**
     * @return boolean
     */
    public static boolean cleanAllBackups() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(" rm -r ./backups");
        cmd.setDirectory(new File("/home"));
        cmd.execute();
        return cmd.getOutput().isEmpty();
    }

    /**
     * @param userName
     * @return boolean
     */
    public static boolean cleanUserBackups(String userName) {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(" rm -r ./backups/" + userName);
        cmd.setDirectory(new File("/home"));
        cmd.execute();
        return cmd.getOutput().isEmpty();
    }

    /**
     * @param groupName
     * @return boolean
     */
    public static boolean removeGroup(String groupName) {
        if (OS.groupExist(groupName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S groupdel " + groupName);
            cmd.execute();
            return cmd.getOutput().trim().isEmpty();
        } else {
            return false;
        }
    }

    /**
     * @param userName
     * @return boolean
     */
    public static boolean removeUser(String userName) {
        if (OS.userExists(userName)) {
            CommandExecutor cmd = new CommandExecutor();
            cmd.addCommand("echo " + CommandExecutor.getPassword() + "|sudo -S userdel --remove " + userName);
            cmd.execute();

            return cmd.getOutput().trim().isEmpty();
        } else {
            return false;
        }
    }

    public static void addChrontab() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addScript("createCrontab.sh");
        cmd.execute();
    }

    /**
     * @return String
     */
    public static String getArchitectura() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("lscpu |awk 'FNR==1 {print $2 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getByteOrder() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("lscpu |awk 'FNR==3 {print $3$4 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getCPUS() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("lscpu |awk 'FNR==5 {print $2 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getThreadsPerCore() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("lscpu |awk 'FNR==7 {print $4 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getThreadsPerSocket() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("lscpu |awk 'FNR==8 {print $4 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getSockets() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("lscpu |awk 'FNR==9 {print $2 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getModel() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(
                "lscpu |awk 'FNR==14 {print $3$4$5$6$7$8 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getMHz() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("lscpu |awk 'FNR==16 {print $3 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getCPUPorcentage() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(
                "top -n 1 -b| awk 'FNR == 3 {print $2+$4+$6+$10 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String[]
     */
    public static String[] getProcesess() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(
                "top -b -n 1| awk 'FNR>6 {print $1\"\t\"$2\"\t\"$9\"\t\"$10\"\t\"$12 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        String[] output = new String[cmd.getOutput().split("\n").length];
        for (int i = 0; i < output.length; i++) {
            output[i] = cmd.getOutput().split("\n")[i].replace("\t", "\n");
        }
        return output;
    }

    /**
     * @return String
     */
    public static String getFreeRAM() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("vmstat -S mb | awk 'FNR==3 {printf $4 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getCache() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("vmstat -S mb | awk 'FNR==3 {printf $4 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getAvailableRAM() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("free -m | awk 'FNR==2{printf $2 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getUsedRAM() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("free -m | awk 'FNR==2{printf $7 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getRAMCache() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("free -m | awk 'FNR==2{printf $6 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getRAMUsage() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("free -m | awk 'FNR==2{printf ($2/$3) > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getDiskFreeSpace() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(
                "df -h --total | awk 'FNR==21 {print $4 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim() + 'B';
    }

    /**
     * @return String
     */
    public static String getDiskUsage() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand(
                "df | grep -i sda | awk 'FNR==1 {print $5 > \"temp.txt\"}' && cat \"temp.txt\" && rm \"temp.txt\"");
        cmd.execute();

        return cmd.getOutput().trim();
    }

    /**
     * @return String
     */
    public static String getStats() {
        CommandExecutor cmd = new CommandExecutor();
        cmd.addCommand("cat /home/Estadisticas/stats.txt");
        cmd.execute();
        return cmd.getOutput();
    }

}
