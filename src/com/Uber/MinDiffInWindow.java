package com.Uber;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Question: Given a size N array, for every K sized rolling window in the array, find the minimum absolute difference between any two elements in the K sized window.
 * https://leetcode.com/discuss/interview-experience/5655907/Uber-or-SDE-2-or-Bangalore-or-July-2024-Offer
 *
 * TC using PQ: O(N log K)
 * Inserting and removing elements from the priority queue takes O(logK) time for each operation.
 * Since we slide the window across the array, the overall complexity becomes O(N log K),
 * where N is the number of elements in the array and K is the size of the sliding window.
 */
class Tuple{
    int val;
    int index;

    public Tuple(int val, int index) {
        this.val = val;
        this.index = index;
    }
}
public class MinDiffInWindow {

    public static void main(String[] args) {

        MinDiffInWindow minDiffInWindow = new MinDiffInWindow();
        int[] input = new int[]{1, 3, 6, 10, 15};
        int k=2;
        //ans: [2, 3, 4, 5]

         int[] input1 = new int[]{7, 1, 3, 4, 9, 6};
         int k2 = 4;
        //ans: [2, 2, 1]

        int[] input3 = new int[]{5, 9, 2, 8, 3};
        int k3 = 3;
        //ans: [1, 1, 2]

        List<Integer> ans = minDiffInWindow.solve(input1,k2);
        for(int i=0; i<ans.size(); i++){
            System.out.print(ans.get(i)+ " ");
        }
    }

    public List<Integer> solve(int[] input, int k){
        List<Integer> ans = new ArrayList<>();

        PriorityQueue<Tuple> pq = new PriorityQueue<>((Tuple a, Tuple b)-> a.val - b.val);

        for(int i=0; i<k; i++){
            pq.offer(new Tuple(input[i],i));
        }
        Tuple smallest = pq.poll();
        Tuple secondSmallest = pq.poll();
        ans.add(Math.abs(smallest.val - secondSmallest.val));
        pq.offer(smallest);
        pq.offer(secondSmallest);

        for(int i=k; i<input.length; i++){
            while(pq.peek().index <= i-k){
                pq.poll();
            }
            pq.offer(new Tuple(input[i],i));
            Tuple t1 = pq.poll();
            Tuple t2 = pq.poll();
            ans.add(Math.abs(t1.val - t2.val));
            pq.offer(t1);
            pq.offer(t2);
        }
        return ans;
    }
}
