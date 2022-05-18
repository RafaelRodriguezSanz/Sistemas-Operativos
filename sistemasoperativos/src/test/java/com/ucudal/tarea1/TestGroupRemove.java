package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGroupRemove {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        OS.createGroup("groupremove");
    }

    @Test
    public void deleteUser() {
        assertTrue(OS.userExists("groupremove"));
        OS.removeGroup("groupremove");
        assertFalse(OS.userExists("groupremove"));
    }


    @After
    public void fin() {
        OS.removeUser("groupremove");
    }

}