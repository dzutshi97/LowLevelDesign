package com.lld.NotifyMe.factory;

import com.lld.NotifyMe.models.ChannelType;
import com.lld.NotifyMe.models.EmailChannel;
import com.lld.NotifyMe.models.SmsChannel;
import com.lld.NotifyMe.strategy.NotificationStrategy;

public class ChannelFactory {

    public static NotificationStrategy getInstance(ChannelType channelType){
        switch(channelType){
            case SMS:
                return new SmsChannel();
            case EMAIL:
                return new EmailChannel();
        }
        throw new RuntimeException("Channel type not supported");
    }
}
