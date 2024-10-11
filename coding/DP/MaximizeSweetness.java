package coding.DP;

import java.util.Arrays;

public class MaximizeSweetness {

    public static void main(String[] args) {
        int[] candies = new int[]{2,2,3,3,3,4};
        int[][] dp = new int[candies.length+1][candies.length+1];
        for (int i = 0; i <= candies.length; i++) {
            Arrays.fill(dp[i], -1);
        }
//        Arrays.sort(candies);
        int ans = solve(-1,0,candies,dp);
        System.out.println(ans);
    }

    public static int solve(int prev, int idx, int[] candies, int[][] dp){
        if(idx >= candies.length){
            return 0;
        }
        if(prev!= -1 && dp[idx][prev] != -1){
            return dp[idx][prev];
        }

        int take = 0, not_take =0;
        if(prev == -1 || (Math.abs(candies[prev] - candies[idx]) > 1 || Math.abs(candies[prev] - candies[idx]) == 0)){
            take = candies[idx] + solve(idx, idx +1 , candies,dp);
        }
        not_take = solve(prev, idx+1, candies,dp);
        if(prev != -1){
            return dp[idx][prev] = Math.max(take, not_take);
        }
        return Math.max(take, not_take);
    }
}



