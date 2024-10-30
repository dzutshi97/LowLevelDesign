package com.Uber.Logging;

import java.util.List;

/**
 * Question: Well defined logging library API with get and set methods. Data was expected to be handled in memory.
 *
 * Performance: First implemented functionally complete solution with appropriate design patterns such as singleton and fluent.
 * Then discussed and partially implemented improvements such as concurrency handling with locking and time complexity optimisation
 * ref - https://medium.com/@manishsakariya/low-level-design-logger-with-java-9c7d522c29d1
 * Question ref - https://leetcode.com/discuss/interview-experience/5655907/Uber-or-SDE-2-or-Bangalore-or-July-2024-Offer
 */
public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getInstance();

        logger.setLogLevel(LogLevel.INFO);
        logger.setLogSink(new ConsoleSink()); //Strategy design pattern where Logger is the context class holding reference to logSink concrete strategy

        logger.log(LogLevel.INFO, "log this @ INFO level - 1");
        logger.log(LogLevel.INFO, "log this @ INFO level - 2");
        logger.log(LogLevel.INFO, "log this @ INFO level- 3");
        logger.log(LogLevel.INFO, "log this @ INFO level - 4");
        logger.log(LogLevel.WARN, "log this @ WARN level - 1");
        logger.log(LogLevel.WARN, "log this @ WARN level - 2");

        System.out.println("FILTERING OUT MESSAGES:");

        List<LogEntry> entries = logger.getLogs(LogLevel.WARN);
        for(LogEntry entry: entries){
            System.out.println(entry.message);
        }

        logger.clearLogs();
    }
}
