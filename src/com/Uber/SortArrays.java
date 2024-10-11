package com.Uber;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode.com/discuss/interview-question/5870876/Uber-or-SDE-2-or-Bangalore-or-Sep-2024-Reject
 * Given a sorted array with positive and negative values, sort them based on the square of its values. Also, print the squared values array.
 *
 *
 * Expected O(N) working code and interviewer made me run the code with sample tests and compare output
 * Sample: [-7, -2, -1, -1, 1, 2, 2, 2, 3, 5] => [-1, -1, 1, -2, 2, 2, 2, 3, 5, -7]
 * Now, given the same array, how would you modify your code to find the kth smallest square element in the array. Expected O(LogN)
 * The problem can be reduced to finding the kth smallest element in union of two sorted arrays similar to median of two sorted arrays
 *
 */
class Solve{

    public ArrayList<Integer>  squareSortArray(int[] arr){

        int left=0, right=0;

        while(right < arr.length && arr[right]< 0){ // right will point to the first +ve no
            right++;
        }
        left = right -1 ;

        for(int i=0;i<arr.length; i++){
//            if(arr[i] < 0){ OR this works as well
//                left=i;
//            }
            arr[i] = arr[i] * arr[i];
        }
        if(left + 1 <arr.length){
            right = left+1;
        }

        ArrayList<Integer> al = new ArrayList<>();

        while( left >= 0 && right < arr.length){

            if(arr[left] <= arr[right]){
                al.add(arr[left]);
                left--;
            } else if (arr[right] <= arr[left]) {
                al.add(arr[right]);
                right++;
            }
        }

        while(left >= 0){
            al.add(arr[left]);
            left--;
        }
        while (right < arr.length){
            al.add(arr[right]);
            right++;
        }
        return al;
    }

    public int getKthSmallest(ArrayList<Integer> input, int k){
        return input.get(k);
    }
}
public class SortArrays {

    public static void main(String[] args) {

        Solve solve = new Solve();
        int[] arr = new int[]{-7, -2, -1, -1, 1, 2, 2, 2, 3, 5};
        System.out.println(solve.squareSortArray(arr));

        int k = 2;
//        System.out.println(solve.getKthSmallest(k));
    }
}
