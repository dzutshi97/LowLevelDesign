package com.lld.NotifyMe.models;

import com.lld.NotifyMe.strategy.NotificationStrategy;

public class SmsChannel extends Channel implements NotificationStrategy {

    private int from;
    private int to;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public void sendNotification(Notification notification) {

    }
}
