package com.Uber.Logging;

public class LogEntry {
    String message;
    long dateTime;
    LogLevel logLevel;

    public LogEntry(LogLevel logLevel, String message) {
        this.logLevel = logLevel;
        this.dateTime = System.currentTimeMillis();
        this.message = message;
    }
}
