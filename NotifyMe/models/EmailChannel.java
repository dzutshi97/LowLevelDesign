package com.lld.NotifyMe.models;

import com.lld.NotifyMe.strategy.NotificationStrategy;

public  class EmailChannel extends Channel implements NotificationStrategy {
    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    @Override
    public void sendNotification(Notification notification) {

    }
}
