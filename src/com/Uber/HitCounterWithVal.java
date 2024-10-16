package com.Uber;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/discuss/interview-question/5838801/Uber-SDE-2-or-Phone-screen
 * I had Uber phone screen last week after OA.
 *
 *
 * I was told it was leetcode style problem for this 60-minute interview. At the beginning, interviewer sort of spent 15 minutes to go over the resume / past experience. The rest is for a leetcode medium question.
 *
 *
 * Question: Implement a Counter class that has the following methods:
 *
 *
 * put(number): put the number to the data structure
 * count(number): count the number of times number was put during the last window=5 minutes
 * countAll(): count the number of times any number was put during the last window=5 minutes.
 * Example:
 * At t = 10PM, put(2)
 * At t = 10:02PM, put(2)
 * At t = 10:03PM, put(3)
 * At t = 10:04PM, count(2) should return 2
 * At t = 10:04PM, countAll() should return 3
 * At t = 10:06PM, count(2) should return 1 (the one that was put at 10:02PM)
 * At t = 10:06PM, countAll() should return 2
 *
 * Variation of this peoblem is LC: Hit Counter. Efficient soln to avoid JVM filling for OOM error - https://leetcode.com/problems/design-hit-counter/solutions/83505/simple-java-solution-with-explanation/comments/629024
 *
 * Follow-ups:
 *
 *
 * If you were to write unit tests, what would they be
 * If your code was to be in production, what issues may it cause?
 * I'm going to onsite.
 */
class Tuple1{
    int val;
    long time;

    public Tuple1(int val, long time) {
        this.val = val;
        this.time = time;
    }
}
public class HitCounterWithVal {
    Deque<Tuple1> deque = new ArrayDeque<>();

    public void put(int no){
        removeOldEntries();
        Tuple1 tuple1 = new Tuple1(no, System.currentTimeMillis());
        deque.addLast(tuple1);
    }
    private void removeOldEntries(){
            long currentTime = System.currentTimeMillis();
            // 1 sec = 1000 ms
            // 1 min = 60 sec
            // 5 min - 5*60 = 300 secs
            // 300 secs = 300*1000 = 300,000 millis
            while(!deque.isEmpty() && (currentTime - deque.peekFirst().time) > 30000){ //300000 is 5 mins, we will take a smaller time for testing in local
                deque.removeFirst();
            }
    }
    public int count(int no){
        removeOldEntries();
        int count=0;
        for(Tuple1 tuple: deque){
            //deque.peekFirst().val
            if(tuple.val == no){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        HitCounterWithVal hitCounter = new HitCounterWithVal();
        hitCounter.put(2);
        hitCounter.put(2);
        hitCounter.put(3);
        hitCounter.put(4);
        int cnt = hitCounter.count(2); //retunr count as 2
        System.out.println(cnt);
        hitCounter.put(5);
        hitCounter.put(5);
        hitCounter.put(2);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int cnt1=hitCounter.count(2); //should return count as 0
        System.out.println(cnt1);

    }
}
/**
 * Overall Time Complexity:
 * put(int no): O(n) in the worst case due to the removeOldEntries() method.
 * count(int no): O(n) in the worst case due to both removeOldEntries() and counting through the deque.
 */
