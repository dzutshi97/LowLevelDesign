package com.lld.TaskScheduler;

public class Main {

    public static void main(String[] args) {

        TaskScheduler scheduler = new TaskScheduler(10);

        // Submit a one-time task with a delay of 5 seconds
        scheduler.schedule("One-time Task", 5000);

        // Schedule a task to run at fixed intervals of 3 seconds
        scheduler.scheduleAtFixedInterval("Fixed Interval Task", 3);

        // Schedule a task to run at fixed rate with an initial delay of 2 seconds and then every 4 seconds
        scheduler.scheduleAtFixedRate("Fixed Rate Task", 2000, 4);

        // Let the scheduler run for a while to see the tasks being executed
        try {
            Thread.sleep(20000); // 20 seconds to observe the output
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown the scheduler gracefully
//        scheduler.shutdown();
    }
}
