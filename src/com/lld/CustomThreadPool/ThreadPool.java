package com.lld.CustomThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool{

    private final LinkedBlockingQueue<Runnable> linkedBlockingQueue;
    private final int queueSize;
    private final int noOfThreads;
    private final AtomicBoolean isShutdown;
    private final Thread[] workers;


    public ThreadPool(int queueSize, int noOfThreads) {
        this.linkedBlockingQueue = new LinkedBlockingQueue<>(queueSize);
        this.queueSize = queueSize;
        this.noOfThreads = noOfThreads;
        this.workers = new Thread[noOfThreads]; //note thread array
        this.isShutdown = new AtomicBoolean(false);

        for(int i=0; i<noOfThreads; i++){
            TaskExecutor taskExecutor = new TaskExecutor(this.linkedBlockingQueue, this.isShutdown);
//            Thread thread = new Thread(taskExecutor);
//            thread.start();
            workers[i] = new Thread(taskExecutor);
            workers[i].start();
        }
    }

    public void submit(Runnable task){
        if (!isShutdown.get()) {
            boolean offered = this.linkedBlockingQueue.offer(task);
            if (!offered) {
                System.err.println("Task rejected (queue is full): " + task);
            }
        }
    }

    public void shutdown(){
        isShutdown.set(true);
        for(int i=0; i<workers.length; i++){
            workers[i].interrupt();
        }
        System.out.println("Thread pool is shutting down.");
    }
}
