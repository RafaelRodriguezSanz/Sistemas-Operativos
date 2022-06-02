package com.ucudal.tarea2.utils;

import com.ucudal.tarea2.CPU;
import com.ucudal.tarea2.Scheduler;

public class OS {
    private Scheduler scheduller;
    private CPU cores;
    private static OS instance;

    private OS(int cores) {
        this.scheduller = new Scheduler();
        this.cores = new CPU(cores);
    }

    public static OS initSystem(int cores) {
        if (instance == null) {
            instance = new OS(cores);
        }
        return instance;
    }

    public static OS getInstance() {
        return instance;
    }

    public void addProcess(Process process) {
        this.scheduller.addProcess(new Job(process));
    }

    private void setScheduller(Scheduler scheduller) {
        this.scheduller = scheduller;
    }

    public Scheduler getScheduler() {
        return this.scheduller;
    }

}
