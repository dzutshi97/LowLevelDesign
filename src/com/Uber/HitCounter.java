package com.Uber;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * LC: Hit Counter.
 * Issue: If huge amount of hits happened at the same timestamp, this solution will takes too much memory since each element in queue is a single hit.
 * Efficient soln to avoid JVM overflow -https://leetcode.com/problems/design-hit-counter/solutions/83505/simple-java-solution-with-explanation/comments/629024
 *
 * Idea:
 * Store count against a timestamp and thus avoid multiple timestamp entries
 *
 */
class HitPair{
    int time;
    int count;

    public HitPair(int time, int count) {
        this.time = time;
        this.count = count;
    }
}
public class HitCounter {
    Deque<HitPair> deque;
    int timeWindow;

    public HitCounter() {
        this.deque = new LinkedList<>();
        this.timeWindow = 5*60;
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        removeOldTimestamps(timestamp);
        if(deque.size()>0 && deque.peekLast().time == timestamp){
            deque.getLast().count++;
        }else {
            deque.addLast(new HitPair(timestamp, 1));
        }
    }
    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        removeOldTimestamps(timestamp);
        int count=0;
        for(HitPair hitCounter: deque){
            count+= hitCounter.count;
        }
        return count;
    }

    private void removeOldTimestamps(int currentTimestamp){
        while(!deque.isEmpty() && currentTimestamp - deque.peekFirst().time >= timeWindow){
                deque.removeFirst();
        }
    }
}
