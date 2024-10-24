package com.lld.TaskScheduler;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Comparable<Task> {

    private int id;
    private long scheduledTime;
    private String taskName;
    private final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    public Task(String taskName) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.scheduledTime = System.currentTimeMillis();
        this.taskName = taskName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(long scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    /**
     * @param o the object to be compared.
     * @return
     * The PriorityBlockingQueue in Java orders elements based on their natural ordering or by a Comparator provided at queue construction time.
     * In the case of scheduling tasks, we want them to be sorted in ascending order of their scheduled time.
     * We achieve this by implementing the Comparable interface in the ScheduledTask class and overriding the compareTo metho
     */
    @Override
    public int compareTo(Task o) { //this will naturally sort by asc order in PQ
        return Long.compare(this.scheduledTime, o.scheduledTime);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
