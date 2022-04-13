package com.ucudal.tarea1;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        //CommandExecutor cmd = CommandExecutor.getInstance();
        //cmd.addCommand("whoami");
        String currentUser = OS.currentUser();
        OS.userExists(currentUser);
        OS.userID(currentUser);
        // OS.cleanAll();
        // OS.createGroup("SO");
        // OS.createUser("SO_User", "r--");
        // OS.backup("SO_User");
        // OS.addPrivileges("SO_User", "r--");
        // OS.removePrivileges("SO_User", "r--");
        // OS.getUserInfo("SO_User");
        // OS.getUsers();
        // OS.getGroups();
        
        
    }
}
