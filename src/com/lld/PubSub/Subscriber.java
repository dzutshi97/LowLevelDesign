package com.lld.PubSub;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Subscriber {

    private int id=1;
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1); // Thread-safe ID generator
    private Topic topic;
    private ExecutorService executor;
    private final int noOfThreads = 3;

    public Subscriber(Topic topic) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.topic = topic;
        this.executor = Executors.newFixedThreadPool(3);
        addSubscriberToTopic(topic);
        // note: start() should be invoked here!!
    }
    private void addSubscriberToTopic(Topic topic){
        topic.getSubscribers().add(this);
    }

//    public int getNextId(){
//        return this.id+1; //how to make this thread safe?
//    }

    public void start(){
        executor.submit(() -> {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Message message = topic.getQueue().take(); // BlockingQueue handles synchronization
                    System.out.println("Consumed: Subscriber- " + id + " message- " + message.getContent());
                }catch (Exception e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }
    public void stop() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
