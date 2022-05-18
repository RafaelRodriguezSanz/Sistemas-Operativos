package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUserCreate {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        
    }

    @Test
    public void createUser() {
        assertFalse(OS.userExists("testuser"));
        OS.createUser("testuser", "pass", false);
        assertTrue(OS.userExists("testuser"));
        OS.removeUser("testuser");
        assertFalse(OS.userExists("testuser"));
    }

}