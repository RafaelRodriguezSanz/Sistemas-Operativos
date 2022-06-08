package com.ucudal.tarea2.utils;

import static com.ucudal.tarea2.utils.ThreadUtils.threadTrace;
import static com.ucudal.tarea2.utils.ThreadUtils.threadWarring;
import static com.ucudal.tarea2.utils.ThreadUtils.threadError;;

public class Job extends Thread {
    private Process process;

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

    public void kill() {
        threadError("Killing...");
        super.stop();
    }

    public void suspending() {
        threadWarring("Suspending...");
        super.suspend();
    }

    public synchronized void block() {
        threadTrace("Blocking...");
        try {
            super.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
