/**
Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair. 
At a time the frog can climb either one or two steps. A height[N] array is also given. 
Whenever the frog jumps from a stair i to stair j, the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute difference. 
We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1.
TUF problem link - https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/
**/

import java.util.Arrays;

public class FrogJumpWithHeight {

    public static int minEnergy(int[] height) {
        int N = height.length;
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = 0; // No energy required to stay on the 0th stair
        return solve(0, height, dp);
    }

    private static int solve(int i, int[] height, int[] dp) {
        if (i == height.length - 1) return dp[i];
        if (dp[i] != -1) return dp[i];
        
        int oneStep = Integer.MAX_VALUE;
        int twoStep = Integer.MAX_VALUE;
        
        if (i + 1 < height.length) {
            oneStep = solve(i + 1, height, dp) + Math.abs(height[i + 1] - height[i]);
        }
        if (i + 2 < height.length) {
            twoStep = solve(i + 2, height, dp) + Math.abs(height[i + 2] - height[i]);
        }
        
        dp[i] = Math.min(oneStep, twoStep);
        return dp[i];
    }

    public static void main(String[] args) {
        int[] height = {10, 20, 30, 10};
        System.out.println("Minimum energy required: " + minEnergy(height)); // Expected output: 20
    }
}
