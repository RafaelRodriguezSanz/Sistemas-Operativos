package com.ucudal.tarea1;

import static org.junit.Assert.*;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
import com.ucudal.tarea1.OS.OS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGroupCreate {

    @Before
    public void init() {
        CommandExecutor.setSudo("rafael", "admin");
        OS.createGroup("somegroup");
    }
    
    @Test
    public void groupExists() {
        assertTrue(OS.groupExist("somegroup"));
    }


    @After
    public void fin() {
        OS.removeGroup("somegroup");
    }

}