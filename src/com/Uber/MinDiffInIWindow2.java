package com.Uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Question: Given a size N array, for every K sized rolling window in the array, find the minimum absolute difference between any two elements in the K sized window.
 * https://leetcode.com/discuss/interview-experience/5655907/Uber-or-SDE-2-or-Bangalore-or-July-2024-Offer
 * https://leetcode.com/discuss/interview-question/1688963/stealth-startup-onsite-min-absolute-diff-between-among-last-k-elements-in-stream-of-integers
 *
 * TC using TreeSet:
 * Inserting elements from the TreeSet takes O(logK) time for each operation.
 * Since we slide the window across the array, the overall complexity becomes O(N log K),
 * where N is the number of elements in the array and K is the size of the sliding window.
 *
 * NOTE: We can directly use Arraylist instead of PQ in the below solution. TC:O(N⋅klogk). Read expln below
 */
public class MinDiffInIWindow2 {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public List<Integer> solve(int[] arr, int k){
        List<Integer> ans = new ArrayList<>();

        for(int i=0; i<arr.length; i++){
            pq.offer(arr[i]); //first step imp

            if(pq.size() > k){ //do this check first
                pq.remove(arr[i-k]);
            }
            /**
             * The version using a PriorityQueue has time complexity
             * O(Nlogk), because each insertion and removal takes
             * O(logk) in a PriorityQueue
             */

            if(pq.size() == k){ //do this check second
                //The PriorityQueue is implemented as a heap, which ensures that the smallest element is at the front of the queue,
                // but it doesn't guarantee the order of other elements when you iterate through it. So convert it into Array List to preserve order while iterating
                ans.add(calcMin(new ArrayList<>(pq)));
                /**
                 *  The conversion itself is
                 * O(k) because you're just copying the elements.
                 */
            }
        }
        return ans;
    }

    public int calcMin(List<Integer> list){
        Collections.sort(list); // Sort the list to ensure correct ordering - IMP
        /**
         * Since an ArrayList is unordered, you would first need to sort the elements in each window before calculating the minimum difference. Sorting the elements takes
         * O(klogk).
         */

        int minDiff = Integer.MAX_VALUE;
        Integer prev = null;

//        for(int val: list){
//            if(prev != null){
//                minDiff = Math.min(minDiff, Math.abs(prev - val));
//            }
//            prev = val;
//        }
        for (int i = 1; i < list.size(); i++) {
            minDiff = Math.min(minDiff, Math.abs(list.get(i) - list.get(i - 1)));
        }
        /**
         * After sorting, you iterate through the sorted ArrayList to find the minimum absolute difference. This takes
         * O(k).
         */
        return minDiff;
    }
    public static void main(String[] args) {
        MinDiffInIWindow2 sol2 = new MinDiffInIWindow2();
        int[] arr = {8, 5, 3, 9, 12, 6, 3};
        int K = 3;
        List<Integer> res = sol2.solve(arr, K);
        System.out.println(res); // Output will be a list of minimum absolute differences for each window.
    }
}
/**
 * Thus, for each window, the overall time complexity is:

 * O(klogk)(for sorting)+O(k)(for minimum difference calculation)= O(klogk)
 *
 * Overall Time Complexity:
 * Since you are processing N−k+1 sliding windows in an array of length
 * N,
 * the overall time complexity is:
 * O((N−k+1)⋅klogk)≈ O(N⋅klogk)
 *
 * FYI/Extra Notes:
 * Insertion TC of ArrayList: Since ArrayList is a dynamically resizable array,
 * removing the first element requires shifting all the remaining elements to the left. This operation takes
 * O(k) in the worst case, where k is the window size.
 */
