package com.Uber.Logging;

public class FileSink implements LogSink{
    @Override
    public void log(LogEntry logEntry) {
        /**
         *  try (PrintWriter writer = new PrintWriter(new FileWriter(filename,true))) {
         *             writer.println(message);
         *         } catch (IOException e) {
         *             e.printStackTrace();
         *         }
         */
    }
}
