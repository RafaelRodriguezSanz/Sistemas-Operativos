package com.ucudal.tarea2.utils;

import static com.ucudal.tarea2.utils.ThreadUtils.threadTrace;

public class Job extends Thread {
    private Process process;

    // final Job Idle = new Job(Process.Idle);

    public Job(Process process) {
        super(process);
        setName(process.getName());
        setPriority(process.getPriority());
    }

    public Process getProcess() {
        return this.process;
    }

    @Override
    public synchronized void start() {
        threadTrace("Deploying...");
        super.start();
    }

    @Override
    public void run() {
        threadTrace("Running...");
        super.run();
    }
}
