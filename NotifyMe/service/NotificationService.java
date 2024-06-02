package com.lld.NotifyMe.service;

import com.lld.NotifyMe.factory.ChannelFactory;
import com.lld.NotifyMe.factory.NotificationFactory;
import com.lld.NotifyMe.models.*;
import com.lld.NotifyMe.strategy.NotificationStrategy;

public class NotificationService {
    public void sendNotification(NotificationType notificationType, ChannelType channelType){//eg: PULL/PUSH notification, SMS/EMAIL Channel
        NotificationStrategy strategy =  ChannelFactory.getInstance(channelType);//todo: you can name getInstance() to constructChannel() for better readability

        Notification notification = NotificationFactory.getInstance(notificationType); //todo: you can name getInstance() to constructNotification() for better readability
        notification.setMessage(new EmailMessage());//even for message we can getInstance?
        strategy.sendNotification(notification);
    }

}
//YAY good code! Strategy + fcatory
