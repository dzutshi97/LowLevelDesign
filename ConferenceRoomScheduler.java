package com.Uber.MeetingScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
/**
Question
Implement a system that can schedule meetings in a predefined set of k conference rooms (numbered 1 to k).


We have a method int scheduleMeeting(startTime, endTime) which returns any available room (out of k rooms) during the time range and reserves it. If none is available, return 0.


Example:
3 rooms
scheduleMeeting called with the below params (with expected output):


(5,7) -> 1
(7,9) -> 1
(8,12) -> 2
(10,11) -> 1
(10,11) -> 3
(13,14) ->1
(11,12) -> 1
(10,15) -> 0 (No room available)
(14,16) -> 1
A few points to keep in mind. These are obvious, but still mentioning just in case:


The scheduling calls won't come in increasing order of time (as can already be seen in the example above)
You won't have access to future scheduling calls at the time of scheduling the present call. You need to schedule things on a first-come-first-served basis.
Intervals are open at the end. So, (5,7), (7,9) can be scheduled in the same room, for instance.
As mentioned already in the requirement above, we can return any available room (not necessarily the least numbered one
*/
public class ConferenceRoomScheduler {

    HashMap<Integer, TreeSet<int[]>> schedules;
    private int n;
//    private static final int MAX_NO_OF_ROOMS = 4;

    public ConferenceRoomScheduler(int n) {
        this.schedules = new HashMap<>();
        this.n = n;
        for(int i=1; i<=n; i++){
            this.schedules.put(i,new TreeSet<>((a,b) -> a[0] - b[0])); // Sort meetings by start time
        }
    }

    public int scheduleMeeting(int startTime, int endTime) {
        int[] curr = new int[]{startTime,endTime};
        boolean canAssign = true;
        for(int room : this.schedules.keySet()){
            canAssign = true;
            TreeSet<int[]> meet = schedules.get(room);
            int[] ceil = meet.ceiling(curr);
            int[] floor = meet.floor(curr);
            if( floor!= null && startTime < floor[1])  canAssign = false;
            if(ceil != null && endTime > ceil[0]) canAssign = false;
            if(canAssign){
                meet.add(curr);
                return room;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        ConferenceRoomScheduler scheduler = new ConferenceRoomScheduler(3);
        System.out.println(scheduler.scheduleMeeting(5, 7));  // Output: 1
        System.out.println(scheduler.scheduleMeeting(7, 9));  // Output: 1
        System.out.println(scheduler.scheduleMeeting(8, 12));  // Output: 2
        System.out.println(scheduler.scheduleMeeting(10, 11));  // Output: 1
        System.out.println(scheduler.scheduleMeeting(10, 11));  // Output: 3
        System.out.println(scheduler.scheduleMeeting(13, 14));  // Output: 1
        System.out.println(scheduler.scheduleMeeting(11, 12));  // Output: 1
        System.out.println(scheduler.scheduleMeeting(10, 15));  // Output: 0
        System.out.println(scheduler.scheduleMeeting(14, 16));  // Output: 1
    }
}
//TC ?
// What if multiple users try to access simultaneously? How to make this concurrent?
//https://leetcode.com/discuss/interview-question/5002679/Uber-or-Onsite-DSA-or-Ad-hoc-meeting-scheduler
