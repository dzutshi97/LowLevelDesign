class Solution {
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];

        for(int[] d: dp){
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        int min = Integer.MAX_VALUE;
        for(int col=0; col<grid[0].length; col++){
            min = Math.min(min, dfs(0,col,grid,dp));
        }
        return min;
    }
    public int dfs(int row, int col, int[][] grid, int[][] dp){
        if(col < 0 || col >= grid[0].length){
            return Integer.MAX_VALUE; //note
        }
        if(dp[row][col] != Integer.MAX_VALUE){
            return dp[row][col];
        }
        if(row == grid.length-1){
            return grid[row][col]; //note - grid & not dp is returned here!
        }
    /**
    Returning grid[row][col] ensures that the DFS function starts with the correct values at the bottom row, which are then used to build up the minimum path sums for higher rows recursively.

    This line serves to establish the base case for the recursive DFS function. It ensures that when the recursion reaches the last row, it returns the value of the current cell directly, providing the foundation upon which the minimum path sums are computed for higher rows.
     */
        int ans = Integer.MAX_VALUE;
        for(int nCol=0; nCol<grid[0].length; nCol++){
            if(col == nCol){
                continue;
            }
            ans = Math.min(ans, grid[row][col] + dfs(row+1, nCol, grid,dp));
        }
        return dp[row][col] =  ans;

    }
}

//https://leetcode.com/problems/minimum-falling-path-sum-ii/solutions/2307254/recursion-memoization/?envType=problem-list-v2&envId=50wob6ze

/**
Approach
The approach used here is a bottom-up dynamic programming approach. The 2D dp array is used to store the minimum path sum to reach each cell.

Base Case: If we have reached the last row (i == grid.length), there is no further path, so the cost is 0.

Memoization: If the result for the current cell is already computed (dp[i][j] != null), we return the precomputed result. This helps in avoiding redundant computations.

Transition: For each cell, we consider all possible cells (a) in the next row (i+1). We skip the cell which is directly below the current cell (j == a) as per the problem constraints. We find the minimum cost path among all possible next cells and add the cost of the current cell (grid[i][j]). This is done using the Math.min function.

Result: The result is the minimum path sum among all possible starting cells in the first row. We initialize the result with the minimum path sum starting from the first cell (solve(0, 0, grid)) and then iterate over the rest of the cells in the first row.
 */
 //https://leetcode.com/problems/minimum-falling-path-sum-ii/solutions/4891560/solution-using-basic-2d-dp-matrix/?envType=problem-list-v2&envId=50wob6ze
