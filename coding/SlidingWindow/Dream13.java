package Service;

import java.util.*;

public class Dream13 {

    public static void main(String[] args) {
        /**
         * Example 1:
         * Input: nums = [1,2,3,3,4,4,5,6], k = 4
         * Output: true
         * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
         *  K.  Count
         *  3 - 2
         *  4 - 2
         */

        Dream13 dream13 = new Dream13();
//        int[] nums = new int[]{1,2,3,3,4,4,5,6};
//        int[] nums = new int[]{3,2,1,2,3,4,3,4,5,9,10,11};
        int[] nums = new int[]{1,2,3,4};
//        int k = 4; //true;
//        int k = 3; //true;
        int k = 3; //false;
        System.out.println(dream13.solve(nums, k));

    }

    public boolean solve(int arr[], int k){

        if(arr.length % k != 0){
            return false;
        }

//        Arrays.sort(arr);
        Map<Integer, Integer> hmap = new TreeMap<>();

        for(int i=0; i<arr.length; i++){
            if(!hmap.containsKey(arr[i])){
                hmap.put(arr[i],0);
            }
            int count = hmap.get(arr[i]);
            hmap.put(arr[i], count+1);
        }

        //get first element from hmap
        int prev = -1;
        int kCount = 0;

        /**
         *  // using iterators
         *         Iterator<Map.Entry<String, String>> itr = gfg.entrySet().iterator();
         *
         *         while(itr.hasNext())
         *         {
         *              Map.Entry<String, String> entry = itr.next();
         *              System.out.println("Key = " + entry.getKey() +
         *                                  ", Value = " + entry.getValue());
         *         }
         */
        Iterator<Map.Entry<Integer, Integer>> itr = hmap.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<Integer, Integer> entry = itr.next();
            int element = entry.getKey();
            if(prev == -1){
                prev = element;
                continue;
            }
            int curr = element;

            if(curr - prev == 1){
                kCount++;
                int cnt = entry.getValue();//hmap.get(element);
                int newCnt = cnt-1;
                if(newCnt == 0){
//                    hmap.remove(element);
                    itr.remove();
                }
                else{
                    hmap.put(element, newCnt); //update count in map
                }
            }
            if(kCount == k){
                return true;
            }
            prev = curr;
        }
        return false;
    }
}
