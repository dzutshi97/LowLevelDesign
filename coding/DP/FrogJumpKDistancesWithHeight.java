/**
Follow up for the question - https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/
Question link - https://takeuforward.org/data-structure/dynamic-programming-frog-jump-with-k-distances-dp-4/
Problem statement -
This is a follow-up question to “Frog Jump” discussed in the previous article. In the previous question,
the frog was allowed to jump either one or two steps at a time.
In this question, the frog is allowed to jump up to ‘K’ steps at a time. If K=4, the frog can jump 1,2,3, or 4 steps at every index.

Similar to ~= LC: Jump Game 2 - https://leetcode.com/problems/jump-game-ii/submissions/1307452722/
**/

import java.util.*;

class FrogJumpKDistancesWithHeight {
    // Recursive function to calculate the minimum cost to reach the end
    // from a given index with at most 'k' jumps.
    static int solveUtil(int ind, int[] height, int[] dp, int k) {
        // Base case: If we are at the end (index n-1), no more cost is needed.
        if (ind == height.length - 1)
            return 0;

        if (dp[ind] != -1)
            return dp[ind];

        int mmSteps = Integer.MAX_VALUE;

        // Loop to try all possible jumps from '1' to 'k'
        for (int j = 1; j <= k; j++) {
            if (ind + j < height.length) {
                int jump = solveUtil(ind + j, height, dp, k) + Math.abs(height[ind] - height[ind + j]);
                mmSteps = Math.min(jump, mmSteps);
            }
        }

        return dp[ind] = mmSteps;
    }

    // Function to find the minimum cost to reach the end of the array
    static int solve(int n, int[] height, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1); 
        return solveUtil(0, height, dp, k); // Start the recursion from the first index
    }

    public static void main(String args[]) {
        int height[] = { 30, 10, 60, 10, 60, 50 };
        int n = height.length;
        int k = 2;
        System.out.println(solve(n, height, k)); 
    }
}
