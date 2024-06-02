package com.lld.ReadWriteLock;

public class ReadWriteLock {

    private int readers=0;
    private int writers =0;
    private int writeRequests=0;


    public synchronized void  acquireReadLock() throws InterruptedException {
        while(writers > 0 || writeRequests > 0){ //if writes are happening or scheduled, then non-reentrant read lock cannot be acquired
            wait();
        }
        readers++;
    }

    public synchronized void releaseReadLock(){
        if(readers == 0){
            throw new IllegalMonitorStateException(" all readers already released locks");
        }
        readers--;
        if(readers == 0){
            //notify the threads blocked on write
            notifyAll();
        }
        System.out.println("Finished releasing read lock");
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        writeRequests++; //here because read cannot be done unless this scheduled write request is processed
        while(readers > 0 || writers >0){
            wait();
        }
        writers++;
        //logic to write
        writeRequests--;
    }

    public synchronized void releaseWriteLock(){
        if(writers == 0){
            throw new IllegalMonitorStateException("all writers already released locks");
        }
        writers--;
    }


}
