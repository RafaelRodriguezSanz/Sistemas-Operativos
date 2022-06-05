package com.ucudal.tarea2;

import com.ucudal.tarea2.utils.Job;
import com.ucudal.tarea2.utils.ThreadUtils;

public class CPU {
    private Core[] cores;

    public CPU(int number) {
        initCores(number);
    }

    public void initCores(int number) {
        this.cores = new Core[number];
        for (int i = 0; i < number; i++) {
            this.cores[i] = new Core();
        }
    }

    public Core[] getCores() {
        return cores;
    }

    public Core getCore(int core) {
        return this.cores[core - 1];
    }

    public void deploy(int core, Job job) {
        getCores()[core - 1].assignCPU(job);
    }

    public void run() {
        try {
            for (Core core : cores) {
                Job next = core.getJob();
                if (next != null && !next.isAlive()) {
                    next.start();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            ThreadUtils.threadError("Exception is caught: " + e.getMessage());
        }
    }

    public void setCores(Job job, int core) {
        this.cores[core].assignCPU(job);
    }

    public int size() {
        return this.cores.length;
    }
}