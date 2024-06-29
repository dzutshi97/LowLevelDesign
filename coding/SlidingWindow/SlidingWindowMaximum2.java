package coding.SlidingWindow;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// LC: 239. Sliding Window Maximum
//Approach1: TC: O(N) - Monotonic deque
//Approach2: TC: O(NlogN) - Priority Queue

 class Solution {
    private class Pair{
        int num;
        int index;

        public Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    static List<Integer> res = new ArrayList<>();
    static PriorityQueue<Pair> pq = new PriorityQueue<>((Pair p1, Pair p2) -> p2.num- p1.num); // max heap

    public List<Integer>  solve(int[] nums, int k) {

        for(int i=0; i<k;i++){
            pq.offer(new Pair(nums[i], i));
        }
        res.add(pq.peek().num);

        for(int i=k; i<nums.length; i++){
            while(!pq.isEmpty() && pq.peek().index <= i-k){
                pq.poll();
            }
            pq.offer(new Pair(nums[i], i));
            res.add(pq.peek().num);
        }
        return res;
    }
}
public class SlidingWindowMaximum2{
    public static void main(String[] args) {

        int[] a = new int[]{1,3,-1,-3,5,3,6,7};
        int k= 3;
        //ans = [3,3,5,5,6,7]
        Solution sol = new Solution();
        List<Integer> ans = sol.solve(a,k);
        System.out.println(ans);
    }
}
