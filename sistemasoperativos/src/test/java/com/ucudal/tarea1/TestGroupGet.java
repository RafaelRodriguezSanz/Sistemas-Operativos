package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGroupGet {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        OS.createGroup("somegroup");
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
        OS.removeGroup("somegroup");
    }

}