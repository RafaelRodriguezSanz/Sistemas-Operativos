package com.ucudal.tarea2.System;

import com.ucudal.tarea2.utils.Job;
import com.ucudal.tarea2.utils.Process;
import com.ucudal.tarea2.utils.ThreadUtils;

public class OS {
    private Scheduler scheduller;
    private CPU cores;
    private static OS instance;

    private OS(int cores) {
        this.cores = new CPU(cores);
        setScheduller(new Scheduler(this.getCoresAmmount()));
    }

    public static OS initSystem(int cores) {
        if (instance == null) {
            System.setOut(new ThreadUtils(System.out, true));
            Thread.currentThread().setName("OS");
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

    public CPU getCores() {
        return this.cores;
    }

    public int getCoresAmmount() {
        return this.cores.size();
    }

    public int getFreeCore() {
        for (int i = 0; i < this.getCoresAmmount(); i++) {
            if (this.getCores().getCore(i + 1).isFree())
                return i;
        }
        return -1;
    }
}
