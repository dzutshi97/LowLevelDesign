package com.Uber;

import java.util.Map;

/**
 * LC: 1642. Furthest Building You Can Reach
 * TODO: Do using PQ
 */
public class FurthestBuilding {

    public int solve(int idx,int bricks, int ladders, int[] height, Integer[][][] dp){
        if(idx > height.length){
            return 0;
        }

        if(idx == height.length-1){
            return idx;
        }
//        if(bricks == 0 && ladders == 0){
//
//        }
        if(dp[idx][bricks][ladders] != null){
            return dp[idx][bricks][ladders];
        }

        int ans = Integer.MIN_VALUE; ///or idx?
        int diff = height[idx+1] - height[idx];

        if(diff > 0){
            int bricksOp = idx;
            if(bricks >= diff) //note
              bricksOp = solve(idx+1,bricks-diff,ladders,height,dp);
            int laddersOp = idx;
            if(ladders > 0)
                laddersOp = solve(idx+1,bricks,ladders-1,height,dp);
             ans = Math.max(bricksOp,laddersOp);
        }else{
             ans = solve(idx+1,bricks,ladders,height,dp);
        }

        dp[idx][bricks][ladders] = ans;
        return dp[idx][bricks][ladders];
    }


    public static void main(String[] args) {

//        int ladders = 2;
//        int bricks = 10;
//        int[] heights = new int[]{4,12,2,7,3,18,20,3,19};

        int ladders = 0;
        int bricks = 17;
        int[] heights = new int[]{14,3,19,3};

        int n = heights.length;
        Integer[][][] dp = new Integer[n+1][bricks+1][ladders+1];
        FurthestBuilding furthestBuilding = new FurthestBuilding();
        System.out.println(furthestBuilding.solve(0,bricks, ladders, heights,dp));

        /**
         * Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
         * Output: 7
         *
         * Input: heights = [14,3,19,3], bricks = 17, ladders = 0
         * Output: 3
         */
    }
}
