package com.lld.MessageQueue;

import com.lld.MessageQueue.Models.Message;
import com.lld.MessageQueue.Models.Publisher;
import com.lld.MessageQueue.Models.Subscriber;
import com.lld.MessageQueue.Models.Topic;
import com.lld.MessageQueue.Service.MessageQueueService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        MessageQueueService service = new MessageQueueService();

        //create topic
        //create subscribers
        //publish
        //consume
        //subscribers can run in infinite loop

        Topic t = new Topic(4);
        Subscriber s = new Subscriber(1);
        Subscriber s2 = new Subscriber(2);
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(s);
        subscribers.add(s2);
        t.setSubscribers(subscribers);
        s.setTopic(t);
        s2.setTopic(t);
        //consume messages by starting subscriber
        Thread consumerThread = new Thread(() -> {
            while(true){
                try {
                    for(int i=0;i<t.getSubscribers().size(); i++){
                        service.consume(t.getSubscribers().get(i));
                    }
//                    Thread.sleep(1000); // Simulate some processing time
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //publish messages
        Thread producerThread = new Thread(() -> {
            for(int i=0;i<10;i++){
                try {
                    service.publish(t,new Message(i));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        consumerThread.start();
        producerThread.start();

    }
}
