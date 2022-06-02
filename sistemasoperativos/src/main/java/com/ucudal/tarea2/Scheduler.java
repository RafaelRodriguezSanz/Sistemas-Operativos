package com.ucudal.tarea2;

import java.util.ArrayList;
import java.util.Random;
import com.ucudal.tarea2.utils.Job;

public final class Scheduler {

    private static Scheduler instance;

    private ArrayList<Job> ready;
    private ArrayList<Job> blockedSuspended;
    private ArrayList<Job> blockedReady;
    private ArrayList<Job> suspended;
    private ArrayList<Job> ended;

    public Scheduler() {
        ArrayList<Job> emptyReady = new ArrayList<>();
        ArrayList<Job> emptyBlockedSuspended = new ArrayList<>();
        ArrayList<Job> emptyBlockedReady = new ArrayList<>();
        ArrayList<Job> emptySuspended = new ArrayList<>();
        ArrayList<Job> emptyEnded = new ArrayList<>();
        setBlockedReady(emptyBlockedReady);
        setBlockedSuspended(emptyBlockedSuspended);
        setEnded(emptyEnded);
        setReady(emptyReady);
        setSuspended(emptySuspended);
    }

    // #region Getters & Setters
    private ArrayList<Job> getBlockedReady() {
        return blockedReady;
    }

    private ArrayList<Job> getBlockedSuspended() {
        return blockedSuspended;
    }

    private ArrayList<Job> getEnded() {
        return ended;
    }

    private ArrayList<Job> getReady() {
        return ready;
    }

    private ArrayList<Job> getSuspended() {
        return suspended;
    }

    private void setBlockedReady(ArrayList<Job> blockedReady) {
        this.blockedReady = blockedReady;
    }

    private void setBlockedSuspended(ArrayList<Job> blockedSuspended) {
        this.blockedSuspended = blockedSuspended;
    }

    private void setEnded(ArrayList<Job> ended) {
        this.ended = ended;
    }

    private void setReady(ArrayList<Job> ready) {
        this.ready = ready;
    }

    private static void setInstance(Scheduler instance) {
        Scheduler.instance = instance;
    }

    private void setSuspended(ArrayList<Job> suspended) {
        this.suspended = suspended;
    }

    // #endregion

    public void addProcess(Job job) {
        this.getReady().add(job);
    }

    public void start() {
        for (Job job : ready) {
            job.start();
        }
    }

    public void getJob() {

    }
}