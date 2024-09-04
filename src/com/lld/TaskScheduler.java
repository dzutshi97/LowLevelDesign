package com.lld.TaskScheduler;

import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Here's why a blocking queue is essential in a task scheduler:
 *
 * 1. Thread Coordination
 * Producer-Consumer Model: Task schedulers often follow a producer-consumer pattern, where one or more threads (producers) generate tasks and place them into the queue, and one or more threads (consumers) take tasks from the queue to execute them. A blocking queue helps coordinate these threads, ensuring that consumers wait when there are no tasks available and wake up when new tasks arrive.
 * Automatic Thread Blocking: If a worker thread (consumer) attempts to take a task from the queue but the queue is empty, the thread will automatically block (wait) until a task is available. This prevents the thread from busy-waiting (constantly checking the queue), which is inefficient and wasteful of CPU resources.
 * 2. Thread Safety
 * Safe Concurrent Access: Blocking queues are designed to handle concurrent access from multiple threads. This ensures that tasks can be added and removed from the queue without risk of data corruption or race conditions, which is critical in a task scheduler where multiple threads might interact with the queue simultaneously.
 * No Manual Synchronization: The blocking queue internally handles the complexities of synchronization, so developers don't need to manually manage locks or other synchronization mechanisms. This reduces the chance of errors like deadlocks and makes the code simpler and more maintainable.
 **/
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
