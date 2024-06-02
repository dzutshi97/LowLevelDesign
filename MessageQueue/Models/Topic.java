package com.lld.MessageQueue.Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Topic {

    List<Subscriber> subscribers;
    List<Message> messages; //shared message queue
    final int MAX_NO_OF_MSGS;


    public int getMAX_NO_OF_MSGS() {
        return MAX_NO_OF_MSGS;
    }

    public Topic(int MAX_NO_OF_MSGS) {
        this.MAX_NO_OF_MSGS = MAX_NO_OF_MSGS;
        this.messages = new ArrayList<>();
        this.subscribers = new LinkedList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
