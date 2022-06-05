package com.ucudal.tarea2;

import java.util.concurrent.TimeUnit;

import com.ucudal.tarea2.utils.Job;
import com.ucudal.tarea2.utils.OS;
import com.ucudal.tarea2.utils.Process;

public class App {
    public static void main(String[] args) {

        Runnable osCore = new Runnable() {
            @Override
            public void run() {
                OS.initSystem(2);
                while (true) {
                    Job job = OS.getInstance().getScheduler().getJob();
                    int freeCore = OS.getInstance().getFreeCore();
                    if (job != null && freeCore != -1) {
                        OS.getInstance().getCores().setCores(job, freeCore);
                        OS.getInstance().getScheduler().setRunningJob();
                        job.start();
                    }
                }
            }

        };

        Runnable osProcessAdder = new Runnable() {
            @Override
            public void run() {
                Process process = null;
                int i = 0;
                while (i != 5) {
                    i++;
                    process = new Process("MyProcess" + i, "Me", 1, 0) {
                        @Override
                        public void process() throws InterruptedException {
                            System.out.println("This is my process" + this.priority);
                            TimeUnit.SECONDS.sleep(5);
                            System.out.println("My Process" + this.priority + " Continues...");
                            TimeUnit.SECONDS.sleep(5);
                            System.out.println("Final Line" + this.priority + "!");
                        }
                    };
                    OS.getInstance().addProcess(process);
                }
            }
        };

        Runnable osBlocker = new Runnable() {
            @Override
            public void run() {
                Process process = null;
                int i = 0;
                while (i != 5) {
                    i++;
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    OS.getInstance().getCores().getCore(1).removeCPU();
                }
            }
        };

        Thread core = new Thread(osCore);
        core.setName("CORE");
        core.start();
        Thread adder = new Thread(osProcessAdder);
        adder.setName("ADDER");
        adder.start();

        Thread blocker = new Thread(osBlocker);
        blocker.setName("BLOCKER");
        blocker.start();
    }
}
