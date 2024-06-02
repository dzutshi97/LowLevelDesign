package com.lld.ReadWriteLock;

import java.util.Random;

public class Worker implements Runnable{
    private final ReadWriteLock lock;
    private static final Random rand = new Random();
    private static final double WRITE_PROB = 0.5;
    private final int id;


    public Worker(ReadWriteLock lock, int id) {
        this.lock = lock;
        this.id = id;
    }

    private void read(){
        try {
            lock.acquireReadLock();

        }catch (Exception e){
            Thread.currentThread().interrupt();
            return;
        }
        //perform read
        try{
            Thread.sleep(500);
        }catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.releaseReadLock();
        }
        System.out.println("Finished reading");
    }

    private void write(){
        try {
            lock.acquireWriteLock();
        }catch (Exception e){
            Thread.currentThread().interrupt();
            return;
        }
        //perform write
        try{
            Thread.sleep(500);
        }catch (Exception e){
            Thread.currentThread().interrupt();
        } finally {
            lock.releaseWriteLock();
        }
        System.out.println("Finished writing");
    }

    private void performAction() {
        double r = rand.nextDouble();
        if (r <= WRITE_PROB) {
            write();
        } else {
            read();
        }
    }

    @Override
    public void run() {
        System.out.println("starting execution of worker...."+ id);
        while(!Thread.currentThread().isInterrupted()){
            performAction();
        }
    }
}
