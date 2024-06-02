package com.lld.NotifyMe.factory;

import com.lld.NotifyMe.models.*;

public class NotificationFactory {
    //get Notification instance -push/ pull or regular
    public static Notification getInstance(NotificationType notificationType){
       switch (notificationType){
           case PUSH:
               return new PushNotification();
           case PULL:
               return new PullNotification();
       }
        throw new RuntimeException("Notification category not supported");
    }
}
