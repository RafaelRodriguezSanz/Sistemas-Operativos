package com.ucudal.tarea2;

import com.ucudal.tarea2.utils.Job;
import com.ucudal.tarea2.utils.OS;
import com.ucudal.tarea2.utils.ThreadUtils;
import static com.ucudal.tarea2.utils.ThreadUtils.threadWarring;

public class Core {
    private Job job;

    public Core() {

    }

    public synchronized Job removeCPU() {
        job.stop();
        threadWarring("Blocking Task" + job.getName());
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
