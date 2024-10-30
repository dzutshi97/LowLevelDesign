package com.Uber.Logging;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {

    private static Logger instance;
    private LogSink logSink;
    private LogLevel logLevel;
    private List<LogEntry> logEntries;

    private Logger() { //note: private constructor
        this.logSink = new ConsoleSink();
        this.logLevel = LogLevel.INFO; //default loglevel
        this.logEntries = new ArrayList<>();
    }

    public static synchronized Logger getInstance(){
        if(instance == null){
            return new Logger();
        }
        return instance;
    }

    public void setLogLevel(LogLevel logLevel){
        this.logLevel = logLevel;
    }

    public void setLogSink(LogSink logSink){
        this.logSink = logSink;
    }

    public void log(String message){ //LogEntry
        LogEntry logEntry = new LogEntry(logLevel, message);
        logEntries.add(logEntry);
        logSink.log(logEntry);
    }

    public void log(LogLevel logLevel, String message){ //LogEntry
        if(logLevel.ordinal() < this.logLevel.ordinal()){
            System.err.println("Log level should be greater than or equal to default/initialized log level");
            return;
        }
        LogEntry logEntry = new LogEntry(logLevel, message);
        logEntries.add(logEntry);
        logSink.log(logEntry);
    }

    public List<LogEntry> getLogs(LogLevel logLevel){
        return logEntries.stream().filter(logEntry -> logEntry.logLevel == logLevel).collect(Collectors.toList());
    }

    // Method to clear all logs
    public void clearLogs() {
        logEntries.clear();
    }

    // Method to determine if a log message should be logged based on current log level
    private boolean shouldLog(LogLevel level) {
        return level.ordinal() >= logLevel.ordinal();
    }
}
