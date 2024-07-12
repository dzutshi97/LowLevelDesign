package com.lld.PubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Topic {

    private String name;
    private final LinkedBlockingQueue<Message> queue;
    private List<Subscriber> subscribers;

    public Topic(String name) {
        this.name = name;
        this.queue = new LinkedBlockingQueue<Message>();
        this.subscribers = new ArrayList<>();
    }

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

}
