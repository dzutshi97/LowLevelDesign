package com.Uber;

/**
 * Uber L4 Phone Screen
 * Question: Given a two dimensional array of positive integer values,
 * find the minimum sum when you start from the top left corner traveling to the bottom right corner. You can only move in the direction of right and down
 *
 * Time: O(m * n), Space: O(m * n)
 * You could further optimize this to only keep track of the rows in the dp table since we only move in the direciton or right and down: (Space O(n)).
 *
 * Follow up question: Same problem however negative integers are allowed, and you can move in all four directions??
 */
public class MinimumPathSum {

    int[][] dirs = new int[][]{{1,0}, {0,1}};//bottom & right

    public int solve( int row, int col, int[][] matrix, Integer[][] dp){

        if(row == matrix.length-1 && col==matrix[0].length-1){
            return matrix[row][col];
        }

        if(dp[row][col] != null){
            return dp[row][col];
        }

        int currSum = Integer.MAX_VALUE;

        for(int[] dir: dirs){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow >= matrix.length || nCol > matrix[0].length - 1){
                continue;
            }
            currSum = Math.min(currSum, matrix[row][col] + solve(nRow,nCol,matrix,dp));
        }
        dp[row][col] = currSum;
        return dp[row][col];
    }
    public static void main(String[] args) {
//        int[][] matrix = new int[][]{
//                {1, 3, 1},
//                {1, 5, 1},
//                {4, 2, 1}
//        }; //ANs: 7
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        }; //Ans: 12
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        Integer[][] dp = new Integer[matrix.length][matrix[0].length];
        int minSum = minimumPathSum.solve(0,0,matrix,dp);
        System.out.println(minSum);
    }
}
