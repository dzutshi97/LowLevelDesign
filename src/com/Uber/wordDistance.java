package com.Uber;

/**
 *
 * Problem statement
 * You are given a document as an Array/List 'ARR' of words of length ‘N’. You have to perform Q queries. In each query, you are given two words. Your task is to find the smallest distance between these two words in a document and return it.
 *
 * Distance between words is defined as the difference between their indexes.
 *
 * For example:
 *
 * ARR=[‘hot’, ‘a’, ‘b’, ‘dog’] and query = (‘hot’, ‘dog’)
 *
 * The answer, in this case, is 3 as the minimum distance between ‘hot’ and ‘dog’ in the given document is 3.
 * Note:
 *
 * If any one of the words is not present in the document then your program must return ‘N’ which is the length of the document.
 * https://naukri.com/code360/problems/word-distance_1229393?topList=top-salesforce-coding-interview-questions&problemListRedirection=true&leftPanelTabValue=PROBLEM
 */
public class WordDistance  {
}
class Solve3 {

    public static int[] wordDistance(int n, int q, String[] arr, String[][] queries) {

        // Write your code here.

        int[] ans = new int[q];

        int index = 0;

        while(index<q){

            int t1=-1,t2=-1;

            String first = queries[index][0];

            String second = queries[index][1];

            int min = Integer.MAX_VALUE;

            for(int i=0;i<n;i++){

                if(arr[i].equalsIgnoreCase(first)){

                    t1 = i;

                }

                if(arr[i].equalsIgnoreCase(second)){

                    t2 = i;

                }

                if(t1 != -1 && t2 != -1){

                    min = Math.min(min, Math.abs(t2-t1));

                } else{

                    min = n;

                }

            }

            ans[index] = min;

            index++;

        }

        return ans;

    }

}
