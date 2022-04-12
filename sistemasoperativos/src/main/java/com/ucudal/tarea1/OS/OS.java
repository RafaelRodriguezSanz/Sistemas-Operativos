package com.ucudal.tarea1.OS;

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
        return "";
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

}
