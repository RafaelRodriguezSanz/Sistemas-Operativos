package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUserRemove {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        OS.createUser("userremove", "pass", false);
    }

    @Test
    public void deleteUser() {
        assertTrue(OS.userExists("userremove"));
        OS.removeUser("userremove");
        assertFalse(OS.userExists("userremove"));
    }


    @After
    public void fin() {
        OS.removeUser("userremove");
    }

}