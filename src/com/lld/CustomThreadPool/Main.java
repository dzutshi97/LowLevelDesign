package com.lld.CustomThreadPool;

public class Main {

    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(5,5);

        for(int i=0; i<10; i++){
            int taskNo = i;
            threadPool.submit(() -> {
                System.out.println("Executing Task " + taskNo + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    System.err.println("Task interrupted: " + e.getMessage());
                }
            });
        }
        // Give some time for all tasks to finish
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted: " + e.getMessage());
        }

        threadPool.shutdown();
        System.out.println("All tasks submitted and thread pool shut down.");

    }
}
