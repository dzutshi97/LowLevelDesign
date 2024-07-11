package com.lld.CustomThreadPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskExecutor implements Runnable {
    private LinkedBlockingQueue<Runnable> blockingQueue;
    private AtomicBoolean isShutdown;


    public TaskExecutor(LinkedBlockingQueue<Runnable> blockingQueue, AtomicBoolean isShutdown) {
        this.blockingQueue = blockingQueue;
        this.isShutdown = isShutdown;
    }

    @Override
    public void run() {
        while (true){ //or -  while (!isShutdown.get() || !blockingQueue.isEmpty())
            try {
                Runnable task =blockingQueue.take();
                if (task != null) {
                    task.run();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
