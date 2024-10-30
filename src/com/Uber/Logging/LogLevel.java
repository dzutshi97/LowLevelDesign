package com.Uber.Logging;

public enum LogLevel {

    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5);

    final int level;

    LogLevel(int leveNo) {
        this.level = leveNo;
    }
}
