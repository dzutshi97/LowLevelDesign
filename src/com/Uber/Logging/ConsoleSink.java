package com.Uber.Logging;

public class ConsoleSink implements LogSink {
    @Override
    public void log(LogEntry logEntry) {
        System.out.println("CONSOLE: "+logEntry.message+ " DATE: "+ logEntry.dateTime);
    }
}
