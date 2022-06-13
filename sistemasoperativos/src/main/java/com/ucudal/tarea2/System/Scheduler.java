package com.ucudal.tarea2.System;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;


import com.ucudal.tarea2.utils.Job;

import java.util.Queue;

public final class Scheduler {

    private static Scheduler instance;

    private Job[] running;
    private Queue<Job>[] ready;
    private ArrayList<Job> blockedSuspended;
    private ArrayList<Job> blockedReady;
    private ArrayList<Job> suspended;
    private ArrayList<Job> ended;

    public Scheduler(int cores) {
        Queue<Job>[] emptyReady = new Queue[100];
        for (int i = 0; i < emptyReady.length; i++) {
            emptyReady[i] = new LinkedList<Job>();
        }
        ArrayList<Job> emptyBlockedSuspended = new ArrayList<>();
        ArrayList<Job> emptyBlockedReady = new ArrayList<>();
        ArrayList<Job> emptySuspended = new ArrayList<>();
        ArrayList<Job> emptyEnded = new ArrayList<>();
        Job[] emptyRunning = new Job[cores];
        setBlockedReady(emptyBlockedReady);
        setBlockedSuspended(emptyBlockedSuspended);
        setEnded(emptyEnded);
        setReady(emptyReady);
        setSuspended(emptySuspended);
        setRunning(emptyRunning);
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

    private Queue<Job>[] getReady() {
        return ready;
    }

    private ArrayList<Job> getSuspended() {
        return suspended;
    }

    private Job[] getRunning() {
        return running;
    }

    private void setRunning(Job[] running) {
        this.running = running;
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

    private void setReady(Queue<Job>[] ready) {
        this.ready = ready;
    }

    private static void setInstance(Scheduler instance) {
        Scheduler.instance = instance;
    }

    private void setSuspended(ArrayList<Job> suspended) {
        this.suspended = suspended;
    }

    // #endregion

    public void addEnded(Job job) {
        this.getEnded().add(job);
    }

    public void addProcess(Job job) {
        this.getReady()[job.getPriority() - 1].add(job);
    }

    public void start() {
        for (int i = 0; i < ready.length; i++) {
            for (Job job : getReady()[i]) {
                job.start();
            }
        }
    }

    public synchronized Job getJob() {
        for (int i = 0; i < getReady().length; i++) {
            if (!getReady()[i].isEmpty()) {
                return getReady()[i].element();
            }
        }
        return null;
    }

    public void setRunningJob() {
        Job job = null;
        for (int j = 0; j < getReady().length; j++) {
            if (!this.getReady()[j].isEmpty()) {
                job = getReady()[j].element();
                getReady()[j].remove();
                break;
            }
        }
        for (int i = 0; i < this.getRunning().length; i++) {
            if (this.getRunning()[i] == null && job != null) {
                this.getRunning()[i] = job;
                break;
            }
        }
    }

    public void addBlocked(Job job) {
        this.getBlockedReady().add(job);
    }

    public void addProcessTime(int seconds, int numberJobs){ 
        Timer timer =  new Timer(seconds * 1000, for ( int i = 0 ; i < seconds ; i++){
        Process proces[i] = new Process("Default" + i, owner.getOwner(), 1, seconds * 2 * 1000);}))
    }
}