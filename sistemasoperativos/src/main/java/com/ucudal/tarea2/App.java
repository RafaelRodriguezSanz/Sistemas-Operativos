package com.ucudal.tarea2;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import com.ucudal.tarea2.utils.Job;
import com.ucudal.tarea2.utils.OS;
import com.ucudal.tarea2.utils.Process;
import com.ucudal.tarea2.utils.ThreadUtils;

public class App {
    public static void main(String[] args) {
        Thread.currentThread().setName("OS");
        System.setOut(new ThreadUtils(System.out, true));
        Process process1 = new Process("MyProcess1", "Me", 1, 0) {

            @Override
            public void process() throws InterruptedException {
                System.out.println("This is my process");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("My Process Continues...");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Final Line!");
            }

        };

        Process process2 = new Process("MyProcess2", "Me", 1, 0) {

            @Override
            public void process() throws InterruptedException {
                System.out.println("This is my process2");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("My Process2 Continues...");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Final Line2!");
            }

        };
        // OS.initSystem(2);
        // OS.getInstance().addProcess(process1);
        // OS.getInstance().addProcess(process2);
        // OS.getInstance().getScheduler().start();
        // try {
        // TimeUnit.SECONDS.sleep(50);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // Thread t1 = new Thread(process1);
        // t1.setName(process1.getName());
        // t1.setPriority(process1.getPriority());
        // Thread t2 = new Thread(process2);
        // t2.setName(process2.getName());
        // t2.setPriority(process2.getPriority());
        // t1.start();
        // t2.start();

        Job t1 = new Job(process1);
        Job t2 = new Job(process2);

        // t1.start();
        // t2.start();

        Scheduler s = new Scheduler();
        s.addProcess(t1);
        s.addProcess(t2);
        s.start();
    }
}
