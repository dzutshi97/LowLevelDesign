package com.lld.PubSub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {

        PubSubService service = new PubSubService();
        service.createTopic("Topic1");
        service.addSubscriber("Topic1"); //Subscriber 1
        service.addSubscriber("Topic1"); //Subscriber 2 - either both should consumer or any one out of them. Depends on the interviewers


        ExecutorService publishExecutor =  Executors.newFixedThreadPool(3);
        publishExecutor.submit(() -> {
            for(int i=0; i<10; i++){
                service.publish("Topic1","msg"+i);
//                sleep(1000); // Simulate delay between publishes
            }
        });

    }
}
