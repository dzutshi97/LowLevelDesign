package com.lld.MessageQueue.Service;

import com.lld.MessageQueue.Models.Message;
import com.lld.MessageQueue.Models.Subscriber;
import com.lld.MessageQueue.Models.Topic;

public interface MessageQueueI {

    public String publish(Topic topic, Message message) throws InterruptedException;
    public void consume(Subscriber subscriber) throws InterruptedException;
}
