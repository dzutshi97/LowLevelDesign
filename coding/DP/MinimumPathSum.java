package coding.DP;

import java.util.Arrays;

import static coding.DP.Solution.solve;

class Solution {

    public static int solve(int[][] grid) {



        //
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], 99999);
        }
        dp[0][0] = grid[0][0];
//        //base case
//        //1st row
//        for(int j=1; j<grid[0].length; j++){
//            dp[0][j] = grid[0][j];
//        }
//        for(int i=1; i<grid.length; i++){
//            dp[i][0] = grid[i][0];
//        }

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){

                int top = 9999;
                int left= 9999;
                if(i>0){
                     top = dp[i-1][j];
                }
                if(j>0){
                     left = dp[i][j-1];
                }
                dp[i][j] = grid[i][j] + Math.min(top, left);
            }
        }
        return dp[grid.length -1][grid[0].length -1];
    }


}
public class MinimumPathSum{

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        int ans = solve(grid);
        System.out.println(ans);
    }
}
