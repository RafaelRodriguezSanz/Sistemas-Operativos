package com.ucudal.tarea2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.ucudal.tarea2.utils.ThreadUtils;

import javafx.scene.paint.Stop;

public class App {
    static long time = 5000;

    public static class Job extends Thread {
        String blockedBy;
        long blockedTime;
        long timeout;
        long interruptionTime;
        long lookInteruption;

        public Job(Tarea runnable, long interruptionTime, long lookInteruption) {
            super(runnable);
            this.timeout = runnable.time;
            this.interruptionTime = interruptionTime;
            this.lookInteruption = lookInteruption;
        }
    }

    public static class Tarea implements Runnable {
        long time;
        String blockedBy;

        public Tarea(long time) {
            this.time = time;
        }

        @Override
        public void run() {
            long init = System.currentTimeMillis();
            System.out.println("Executing");
            while (!Thread.currentThread().isInterrupted() && System.currentTimeMillis() < init + this.time) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("Finish!");
        }
    }

    public static abstract class Blocker implements Runnable {
        Job job;
        Unblocker unblocker;

        public Blocker(Job job) {
            this.job = job;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(this.job.interruptionTime);
                    block();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        abstract void block();
    }

    public static abstract class Unblocker implements Runnable {
        Job job;
        Blocker blocker;

        public Unblocker(Job job) {
            this.job = job;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(this.job.interruptionTime);
                    unblock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        abstract void unblock();
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        System.setOut(new ThreadUtils(System.out, true));
        Runnable add = new Runnable() {
            @Override
            public void run() {
                final Scanner userInput = new Scanner(System.in);
                System.out.println("Enter cores:\n");
                while (!userInput.hasNext())
                    ;
                Integer cores = Integer.parseInt(userInput.nextLine());
                final Job[] executing = new Job[cores];
                class ThreadComparator implements Comparator<Job> {

                    @Override
                    public int compare(Job o1, Job o2) {
                        int res = 0;
                        if (o1 == null || o2 == null) {
                            return 1;
                        }
                        if (o1.getPriority() > o2.getPriority()) {
                            res = 1;
                        }
                        if (o1.getPriority() < o2.getPriority()) {
                            res = -1;
                        }
                        if (o1.getPriority() == o2.getPriority()) {
                            res = 0;
                        }
                        return res;
                    }
                }
                final PriorityQueue<Job> ready = new PriorityQueue<>(100, new ThreadComparator());
                final ArrayList<Job> blocked = new ArrayList<>();
                ArrayList<Job> suspended = new ArrayList<>();
                final ArrayList<Job> finished = new ArrayList<>();
                Runnable cleaner = new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            for (int i = 0; i < executing.length; i++) {
                                if (executing[i] != null && executing[i].getState() == Thread.State.TERMINATED) {
                                    finished.add(executing[i]);
                                    executing[i] = null;
                                }
                            }
                        }
                    }
                };
                Thread clean = new Thread(cleaner);
                clean.setName("Cleaner");
                clean.start();
                Runnable deploy = new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            for (int i = 0; i < executing.length; i++) {
                                synchronized (executing) {
                                    if (executing[i] == null) {
                                        Job next = null;
                                        if (!ready.isEmpty()) {
                                            next = ready.poll();
                                        }
                                        if (next != null) {
                                            synchronized (executing) {
                                                executing[i] = next;
                                                try {
                                                    next.start();
                                                } catch (Exception e) {
                                                    next.resume();
                                                }
                                            }
                                            break;
                                        } else {
                                            ready.peek();
                                        }
                                    }
                                }
                            }
                        }
                    }
                };
                Thread deployer = new Thread(deploy);
                deployer.setName("Deployer");
                deployer.start();

                System.out.println("Use add to add processes");
                System.out.println("Use block to block processes");
                System.out.println("Use suspend to suspend processes");
                System.out.println("Use unsuspend to unsuspend processes");
                System.out.println("Use unblock to unblock processes");
                System.out.println("Use kill to kill processes");
                System.out.println("Use priority to change the  priority of a processes");
                System.out.println("Use show to see all process states");
                System.out.println("Use show-nexts to see blocked processes");
                while (true) {
                    while (!userInput.hasNext())
                        ;

                    switch (userInput.nextLine()) {
                        case "add":
                            System.out.println("Give me CSV Values");
                            while (!userInput.hasNext())
                                ;
                            String[] input = userInput.nextLine().split(",");
                            for (int a = 0; a < input.length; a += 6) {
                                ArrayList<Job> OSThreads = new ArrayList<>();
                                ArrayList<Job> UserThreads = new ArrayList<>();
                                Integer priority = Integer.parseInt(input[a]);
                                String name = input[a + 1];
                                String owner = input[a + 2];
                                long executionTime = Long.parseLong(input[a + 3]);
                                long interruptionTime = Long.parseLong(input[a + 4]);
                                long lookInteruption = Long.parseLong(input[a + 5]);
                                Job thread = new Job(new Tarea(executionTime), interruptionTime, lookInteruption);
                                Blocker blocker = new App.Blocker(thread) {
                                    @Override
                                    void block() {
                                        while (thread.getState() != Thread.State.TERMINATED) {
                                            try {
                                                Thread.currentThread().sleep(thread.interruptionTime);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            for (int j = 0; j < executing.length; j++) {
                                                if (executing[j] != null && executing[j].equals(thread)) {
                                                    synchronized (thread) {
                                                        thread.suspend();
                                                        thread.blockedBy = "OS";
                                                    }
                                                    synchronized (blocked) {
                                                        if (!blocked.contains(thread)) {
                                                            blocked.add(thread);
                                                        }
                                                    }
                                                    executing[j] = null;
                                                    break;
                                                }
                                            }
                                            Thread unblockTask = new Thread(unblocker);
                                            unblockTask.run();
                                        }
                                        Thread.currentThread().stop();
                                    }
                                };
                                Unblocker unblocker = new App.Unblocker(thread) {
                                    @Override
                                    void unblock() {
                                        while (thread.getState() != Thread.State.TERMINATED) {
                                            try {
                                                Thread.currentThread().sleep(thread.lookInteruption);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            for (int j = 0; j < blocked.size(); j++) {
                                                synchronized (blocked) {
                                                    if (blocked.get(j).blockedBy.equals("OS")) {
                                                        ready.add(thread);
                                                        blocked.remove(thread);
                                                        break;
                                                    }
                                                }
                                            }
                                            Thread blockTask = new Thread(blocker);
                                            blockTask.run();
                                        }
                                        Thread.currentThread().stop();
                                    }
                                };
                                blocker.unblocker = unblocker;
                                unblocker.blocker = blocker;

                                Thread blockerSequence = new Thread(blocker);
                                blockerSequence.setName("Blocker");
                                blockerSequence.start();

                                if (owner == "OS") {
                                    OSThreads.add(thread);
                                } else {
                                    UserThreads.add(thread);
                                }
                                thread.setName(name);
                                thread.setPriority(priority);
                                ready.add(thread);
                            }
                            break;
                        case "suspend":
                            System.out.println("Give me index of executing process to suspend");
                            while (!userInput.hasNext())
                                ;
                            Integer g = Integer.parseInt(userInput.nextLine());
                            synchronized (executing[g]) {
                                executing[g].suspend();
                            }
                            synchronized (suspended) {
                                suspended.add(executing[g]);
                            }
                            executing[g] = null;
                            break;
                        case "unsuspend":
                            System.out.println("Give me index of suspended process to unsuspend");
                            try {
                                while (!userInput.hasNext())
                                    ;
                                Integer m = Integer.parseInt(userInput.nextLine());
                                synchronized (suspended) {
                                    suspended.get(m).resume();
                                }
                                for (int j = 0; j < executing.length; j++) {
                                    if (executing[j] == null) {
                                        synchronized (suspended) {
                                            executing[j] = suspended.get(m);
                                            suspended.remove(j);
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "block":
                            System.out.println("Give me index of running process to block");
                            try {
                                while (!userInput.hasNext())
                                    ;
                                Integer p = Integer.parseInt(userInput.nextLine());
                                synchronized (executing[p]) {
                                    executing[p].suspend();
                                    executing[p].blockedBy = "User";
                                }
                                synchronized (blocked) {
                                    blocked.add(executing[p]);
                                }
                                executing[p] = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "kill":
                            System.out.println("Give me index of running process to kill");
                            try {
                                while (!userInput.hasNext())
                                    ;
                                Integer l = Integer.parseInt(userInput.nextLine());
                                synchronized (executing[l]) {
                                    executing[l].stop();
                                }
                                finished.add(executing[l]);
                                executing[l] = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "unblock":
                            System.out.println("Give me index of blocked process to unblock");
                            try {
                                while (!userInput.hasNext())
                                    ;
                                Integer m = Integer.parseInt(userInput.nextLine());
                                synchronized (blocked) {
                                    blocked.get(m).resume();
                                }
                                for (int j = 0; j < executing.length; j++) {
                                    if (executing[j] == null && executing[j].blockedBy.equals("User")) {
                                        synchronized (blocked) {
                                            executing[j] = blocked.get(m);
                                            blocked.remove(j);
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "priority":
                            System.out.println("Give me index of ready process to change it priority");
                            try {
                                while (!userInput.hasNext())
                                    ;
                                int i = Integer.parseInt(userInput.nextLine());
                                System.out.println("Give me the new priority");
                                while (!userInput.hasNext())
                                    ;
                                int priority = Integer.parseInt(userInput.nextLine());
                                ((Job[]) ready.toArray())[i].setPriority(priority);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "show":
                            try {
                                String representation = "";
                                representation += "\n\n-Executing-\n";
                                boolean isEmpty = true;
                                for (int n = 0; n < executing.length; n++) {
                                    if (executing[n] != null) {
                                        isEmpty = false;
                                    }
                                }
                                if (executing.equals(null) || executing.length == 0 || isEmpty) {
                                    representation += "\t*No Process*\n";
                                } else {
                                    for (int v = 0; v < executing.length; v++) {
                                        if (executing[v] != null) {
                                            representation += "\t-" + executing[v].getName() + '\n';
                                        }
                                    }
                                }
                                representation += "-Blocked-\n";
                                if (blocked.equals(null) || blocked.isEmpty()) {
                                    representation += "\t*No Process*\n";
                                } else {
                                    for (Job blockedThread : blocked) {
                                        representation += "\t-" + blockedThread.getName() + '\n';
                                    }
                                }
                                representation += "-Suspended-\n";
                                if (suspended.equals(null) || suspended.isEmpty()) {
                                    representation += "\t*No Process*\n";
                                } else {
                                    for (Job suspendedThread : suspended) {
                                        representation += "\t-" + suspendedThread.getName() + '\n';
                                    }
                                }
                                representation += "-Finished-\n";
                                if (finished.equals(null) || finished.isEmpty()) {
                                    representation += "\t*No Process*\n";
                                } else {
                                    for (Job finishedThread : finished) {
                                        representation += "\t-" + finishedThread.getName() + '\n';
                                    }
                                }
                                representation += "-Ready-\n";
                                if (ready.equals(null) || ready.isEmpty()) {
                                    representation += "\t*No Process*\n";
                                } else {
                                    for (int i = 0; i < ready.size(); i++) {
                                        representation += "\t-#" + i + ((Job) ready.toArray()[i]).getPriority()
                                                + ((Job) ready.toArray()[i]).getName()
                                                + '\n';
                                    }
                                }
                                System.out.println(representation);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "show-nexts":
                            try {
                                String representation = "";
                                representation += "\n\n-Nexts-\n";
                                if (blocked.isEmpty()) {
                                    representation += "\t*No Process*\n";
                                } else {
                                    for (int i = 0; i < blocked.size(); i++) {
                                        representation += "\t" + i + ")" + blocked.get(i).blockedBy
                                                + "-"
                                                + blocked.get(i).getPriority()
                                                + blocked.get(i).getName()
                                                + '\n';
                                    }
                                }
                                System.out.println(representation);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

        };

        Thread OS = new Thread(add);
        OS.setName("OS");
        OS.start();
    }

}
