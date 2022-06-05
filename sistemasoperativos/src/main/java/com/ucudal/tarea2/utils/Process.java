package com.ucudal.tarea2.utils;

import java.time.LocalDate;
import java.util.PrimitiveIterator;

import static com.ucudal.tarea2.utils.ThreadUtils.threadTrace;
import static com.ucudal.tarea2.utils.ThreadUtils.threadError;
import static com.ucudal.tarea2.utils.ThreadUtils.threadWarring;;

public abstract class Process implements Runnable {
    // public static final Process Idle = new Process("Idle", "OS", 0, 1) {
    // @Override
    // public void process() {
    // while (true) {
    // threadTrace("Idleing");
    // }
    // }

    // };

    public String name;
    public String owner;
    public int priority;
    public LocalDate creation;
    public int timeout;

    protected Process(String name, String owner, int priority, int timeout) {
        setName(name);
        setOwner(owner);
        setPriority(priority);
        setCreation(LocalDate.now());
        setTimeout(timeout);
    }

    protected Process() {
        setName("");
        setOwner("");
        setPriority(0);
        setCreation(LocalDate.now());
        setTimeout(0);
    }

    // #region Setters & Getters

    public LocalDate getCreation() {
        return creation;
    }

    public int gettimeout() {
        return timeout;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public int getPriority() {
        return priority;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        threadTrace("Starting Job...");
        try {
            process();
        } catch (Exception e) {
            threadError("Exception throw...");
            e.printStackTrace();
            threadWarring("Ending Job...");
            return;
        }
        threadTrace("Ending Job...");
    }

    public abstract void process() throws Exception;

}
