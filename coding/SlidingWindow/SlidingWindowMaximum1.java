package coding.SlidingWindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// LC: 239. Sliding Window Maximum
//Approach1: TC: O(N) - Monotonic deque
//Approach2: TC: O(NlogN) - Priority Queue
public class SlidingWindowMaximum1 {

    //first element in deque would always be the greatest element
    static Deque<Integer> dq = new ArrayDeque<>();
    static List<Integer> res = new ArrayList<>();
    public static List<Integer>  solve(int[] nums, int k){

//        dq.addFirst(0);
        for(int i=0; i<k; i++){
            while(!dq.isEmpty() &&  nums[i] >= nums[dq.peekLast()]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        res.add(nums[dq.peekFirst()]);

        for(int i=k; i<nums.length; i++){
            if(dq.peekFirst() == i-k){
                dq.pollFirst();
            }
            while(!dq.isEmpty() && nums[i] > nums[dq.peekLast()]){
                dq.pollLast();
            }
            dq.offerLast(i);
            res.add(nums[dq.peekFirst()]);
        }
        return res;

    }

    public static void main(String[] args) {

        int[] a = new int[]{1,3,-1,-3,5,3,6,7};
        int k= 3;
        //ans = [3,3,5,5,6,7]
        List<Integer> ans = solve(a,k);
        System.out.println(ans);
    }
}
