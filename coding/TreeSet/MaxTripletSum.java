package coding.TreeSet;


/**
 * Question: https://www.geeksforgeeks.org/find-maximum-sum-triplets-array-j-k-ai-aj-ak/
 * Use TreeSet apporoach to solve. TC- (n logn) - Modify the treeset approach in gfg above. It seems wrong. Traverse from left to right and use treeset lower() to get
 * greatedt value lower than this element from left. Then, traverse from right to left and use lower() to get greatest value less than the current from right ahnd side.
 * Then traverse array and do max = Math.max( max, L[i] + arr[i] + R[i]). return max.
 *
 * TC explanation: ombining these, the dominant operations inside the loop are lowValue.lower(arr[i]) and lowValue.add(arr[i]), both of which are
 * O(logn). Since these are called in each iteration, the overall time complexity for this loop is
 * O(nlogn).
 *
 * OR much better approach in this ~= question - https://www.geeksforgeeks.org/given-array-three-numbers-maximize-x-ai-y-aj-z-ak/
 *
 * Similar question asked in Uber ->  todo - https://www.youtube.com/watch?v=0Vmtmqa9Og0 Round 2 question from this video
 *
 * suffimax - https://leetcode.com/problems/trapping-rain-water/solutions/771590/c-prefixmaxsuffixmax-concept-dp-100-time/
 */
public class MaxTripletSum {

    public static int solve(int[] arr, int x, int y, int z){
        int n = arr.length;
        if (n < 3) return Integer.MIN_VALUE;

        int ans=Integer.MIN_VALUE;
        int[] suffixMax = new int[arr.length];
        suffixMax[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--){
            suffixMax[i] = Math.max(suffixMax[i+1], arr[i]);
        }

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for(int i=1; i<arr.length; i++){
            dp[i] = Math.max(dp[i-1], arr[i]);
        }

        for(int j=1; j<arr.length-1; j++){
            int maxLeft = dp[j-1]; //satisfy i<j<k condtion
            int maxRight = suffixMax[j+1]; //satisfy i<j<k condtion
            ans = Math.max(ans, maxLeft*x + arr[j]*y + maxRight*z);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int x= 1;
        int y=2;
        int z=3;
        int ans = solve(a,x,y,z);
        System.out.println(ans);
        //ans - 26
    }
}
