package com.ucudal.tarea2;

import com.ucudal.tarea2.utils.Job;
import com.ucudal.tarea2.utils.Process;

public class Core {
    private Job job;

    public Core() {

    }

    public Job removeCPU() throws InterruptedException {
        job.wait();
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

}
