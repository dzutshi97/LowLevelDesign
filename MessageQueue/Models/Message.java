package com.lld.MessageQueue.Models;

public class Message {
    int msgId;
    int consumedByAll = 0;


    public int getConsumedByAll() {
        return consumedByAll;
    }

    public void setConsumedByAll(int consumedByAll) {
        this.consumedByAll = consumedByAll;
    }

    public Message(int msgId) {
        this.msgId = msgId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }
}
