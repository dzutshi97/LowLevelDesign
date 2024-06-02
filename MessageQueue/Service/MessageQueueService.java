package com.lld.MessageQueue.Service;

import com.lld.MessageQueue.Models.Message;
import com.lld.MessageQueue.Models.Subscriber;
import com.lld.MessageQueue.Models.Topic;

import java.util.List;

public class MessageQueueService implements MessageQueueI{

    //create topic
    //create subscribers
    //publish
    //consume

    //subscribers can run in infinite loop
//    public void createTopic(int maxNoOfMsgs){
//        Topic topic = new Topic(5);
//    }

    public synchronized void consume(Subscriber subscriber) throws InterruptedException {
        Topic topic = subscriber.getTopic();
        List<Message> messages = topic.getMessages();
        while(messages.isEmpty()){
            //wait
            wait();
        }
        System.out.println("Message consumed ="+messages.get(0).getMsgId()+ "by the subscriber ="+subscriber.getId());
        Message msg = messages.get(0);
        int consumedSoFar = msg.getConsumedByAll();
        consumedSoFar++;
        msg.setConsumedByAll(consumedSoFar);
        if(consumedSoFar == subscriber.getTopic().getSubscribers().size()){
            messages.remove(0); //message processed and removed from topic
        }
        notify();
    }
    public synchronized String publish(Topic topic, Message message) throws InterruptedException {
        List<Message> messages = topic.getMessages();
        while(messages.size() >= topic.getMAX_NO_OF_MSGS()){
            //wait
            wait();
        }
        messages.add(message);
        notify();
        System.out.println("Message published ->"+message.getMsgId());
        return message.toString();
    }

}
