package com.lld.ReadWriteLock;

public class Main {

    public static void main(String[] args) {
        ReadWriteLock lock = new ReadWriteLock();

        // Create some reader threads
        for (int i = 0; i < 5; i++) {
            Thread readerThread = new Thread(() -> {
                try {
                    lock.acquireReadLock();
                    System.out.println(Thread.currentThread().getName() + " is reading...");
                    Thread.sleep(1000); // Simulate reading
                    lock.releaseReadLock();
                    System.out.println(Thread.currentThread().getName() + " finished reading.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            readerThread.start();
        }

        // Create some writer threads
        for (int i = 0; i < 2; i++) {
            Thread writerThread = new Thread(() -> {
                try {
                    lock.acquireWriteLock();
                    System.out.println(Thread.currentThread().getName() + " is writing...");
                    Thread.sleep(2000); // Simulate writing
                    lock.releaseWriteLock();
                    System.out.println(Thread.currentThread().getName() + " finished writing.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            writerThread.start();
        }
    }
}

//
//public class Main {
//    public static void main(String[] args) {
//
//        ReadWriteLock sharedLock = new ReadWriteLock();
//
//        final int MAX_WOKER_LIMIT = 10;
//        Thread[] workers = new Thread[MAX_WOKER_LIMIT];
//        for(int i=0;i<MAX_WOKER_LIMIT;i++){
//            workers[i] = new Thread(new Worker(sharedLock, i+1));
//        }
//
//        for(Thread t: workers){
//            t.start();
//        }
//
//        try{
//            Thread.sleep(100000);
//        }catch (InterruptedException e){
//            Thread.currentThread().interrupt();
//        }
//
//        //stopping workers
//        System.out.println("Stopping workers...");
//        for(Thread t: workers){
//            t.interrupt();
//        }
//
//    }
//}
///***
// * Comments below in: https://stackoverflow.com/questions/49372668/implementing-a-resource-read-write-lock-in-java
// * Why is numberOfReaders not volatile? Isn't it possible that its value may be cached in each thread? –
// * @nkr there is no need for volatile when every access is inside a synchronized method.
// */