package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        OS.createUser("userexists", "pass", false);
        OS.createUser("userremove", "pass", false);
        OS.createUser("someuser", "pass", false);
        OS.createGroup("somegroup");
    }

    @Test
    public void createUser() {
        assertFalse(OS.userExists("testuser"));
        OS.createUser("testuser", "pass", false);
        assertTrue(OS.userExists("testuser"));
        OS.removeUser("testuser");
        assertFalse(OS.userExists("testuser"));
    }

    @Test
    public void userExists() {
        assertTrue(OS.userExists("userexists"));
    }

    @Test
    public void deleteUser() {
        assertTrue(OS.userExists("userremove"));
        OS.removeUser("userremove");
        assertFalse(OS.userExists("userremove"));
    }

    @Test
    public void allUsers() {
        String[] users = OS.getUsers();
        assertNotNull(users);
        assertNotEquals(users.length, 0);
        boolean flag = false;
        for (String user : users) {
            if (user.contains("someuser")) {
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void allGroups() {
        String[] users = OS.getGroups();
        assertNotNull(users);
        assertNotEquals(users.length, 0);
        boolean flag = false;
        for (String user : users) {
            if (user.contains("somegroup")) {
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @After
    public void fin() {
        OS.removeUser("userexists");
        OS.removeUser("userremove");
        OS.removeUser("someuser");
        OS.removeGroup("somegroup");
    }

}