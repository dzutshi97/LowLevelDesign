package com.lld.PubSub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PubSubService {

    ConcurrentHashMap<String, Topic> topics = new ConcurrentHashMap<>();
    List<Subscriber> subscribers = new ArrayList<>();


    public void createTopic(String name){
        Topic topic = new Topic(name);
        topics.put(name, topic);
    }

    public void addSubscriber(String topicName){
        if(!topics.containsKey(topicName)){
            return;
        }
        Topic topic = topics.get(topicName);
        Subscriber subscriber = new Subscriber(topic);
        subscribers.add(subscriber);
        subscriber.start(); //start the subscriber
    }

    public void publish(String topicName, String message){
        if(!topics.containsKey(topicName)){
            System.err.println("Topic " + topicName + " does not exist");
            return;
        }
        Topic topic = topics.get(topicName);
        Message msg = new Message(message);
        boolean offered = topic.getQueue().offer(msg);
        if(!offered){
            System.err.println("cannot add to a topic which is already full");
        }
        System.out.println("Published: message- "+msg.content);
    }
}
