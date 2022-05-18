package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUserGet {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        OS.createUser("someuser", "pass", false);
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

    @After
    public void fin() {
        OS.removeUser("someuser");
    }

}