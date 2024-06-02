package com.lld.NotifyMe.strategy;

import com.lld.NotifyMe.models.Notification;

public interface NotificationStrategy {
     void sendNotification(Notification notification);
}
