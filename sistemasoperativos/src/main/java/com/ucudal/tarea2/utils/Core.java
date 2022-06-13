package com.ucudal.tarea2.utils;

import static com.ucudal.tarea2.utils.ThreadUtils.threadWarring;

import com.ucudal.tarea2.System.OS;

public class Core {
    private Job job;

    public Core() {
        this.setJob(new Job(null));
    }

    public synchronized Job removeCPU() {
        job.block();
        OS.getInstance().getScheduler().addBlocked(job);
        this.job = null;
        return this.job;
    }

    public void assignCPU(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean isFree() {
        Job job = this.getJob();
        if (job != null) {
            return !this.getJob().isAlive();
        } else {
            return true;
        }
    }
}
