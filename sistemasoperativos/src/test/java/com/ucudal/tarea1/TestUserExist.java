package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUserExist {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        OS.createUser("userexists", "pass", false);
    }

    @Test
    public void userExists() {
        assertTrue(OS.userExists("userexists"));
    }


    @After
    public void fin() {
        OS.removeUser("userexists");
    }

}