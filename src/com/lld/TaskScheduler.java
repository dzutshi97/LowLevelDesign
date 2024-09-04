package com.lld.TaskScheduler;

import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {

    private final PriorityBlockingQueue<Task> queue;
    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;
    /**
     * Using a ReentrantLock around the queue.take() and Thread.sleep() might not be necessary,
     * as PriorityBlockingQueue is already thread-safe, and the critical section is handled by the blocking queue itself.
     */
    private final ReentrantLock lock;


    public TaskScheduler(int queueSize) {
//        queue = new PriorityBlockingQueue<>((Task a, Task b) -> a.getScheduledTime() - b.getScheduledTime());
        queue = new PriorityBlockingQueue<>(queueSize);
        this.lock = new ReentrantLock();
        this.executorService = Executors.newFixedThreadPool(2);
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        start();
    }

    public void submitTask(Task task){
        queue.put(task);
    }

    public void start(){
        executorService.execute(() -> { //execute or submit?
            while(!Thread.currentThread().isInterrupted()){
                lock.lock();
                try {
                    Task task= queue.take();
                    long currentTime = System.currentTimeMillis();
                    long delay = task.getScheduledTime() - currentTime;
                    if (delay > 0) {
                        Thread.sleep(delay);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            }
        });
    }

    /**
     * Creates and executes a one-shot action that becomes enabled after the given delay.
     */
    public void schedule(String taskName, long delay){
        Task task = new Task(taskName);
        long scheduledTime = System.currentTimeMillis() + delay;
        task.setScheduledTime(scheduledTime);
        queue.put(task);
    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given delay between the termination of one execution and the commencement of the next.
     */
    public void scheduleAtFixedInterval(String taskName, long intervalDelayInSeconds){
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long currentScheduledTime = System.currentTimeMillis();
            Task task = new Task(taskName);
            task.setScheduledTime(currentScheduledTime);
            queue.put(task);
        },0,intervalDelayInSeconds, TimeUnit.SECONDS);
    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given period; that is executions will commence after initialDelay then
     * initialDelay+period, then initialDelay + 2 * period, and so on.
     */
    public void scheduleAtFixedRate(String taskName, long initialDelay, long intervalDelayInSeconds){
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long currentTimeInMillis = System.currentTimeMillis();
            Task task = new Task(taskName);
            task.setScheduledTime(2 * currentTimeInMillis);
            queue.put(task);
        }, initialDelay, intervalDelayInSeconds, TimeUnit.SECONDS);
    }

    public void shutdown() {
//        scheduler.shutdown();
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
