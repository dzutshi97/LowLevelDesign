class Solution {
    public int jump(int[] nums) {
     int n= nums.length;
     int dp[]= new int[n+1];
     Arrays.fill(dp,-1); 
    return jump(0,n,nums,dp);
    }
    int  jump(int ind,int n,int nums[],int dp[])
    {
        int ans=0; //note
        int  min=Integer.MAX_VALUE; //min for jumps at a particular index - i.e ind
        if(ind>=n-1)
        {
            return 0;
        }
        if(dp[ind]!=-1)
        {
            return dp[ind];
        }
        for(int i=1;i<=nums[ind];i++) //nums[ind] - solve for subproblems in DP, till you reach last index
        {
            ans = (jump(ind+i,n,nums,dp));
            /**
            Using nums[ind] + i would be incorrect in the above line because nums[ind] represents the maximum jump length you can make from the current index, not a new index position.
             */
            if(ans != Integer.MAX_VALUE){
                min= Math.min(1 + ans,min);
            }
        }
        dp[ind]= min;
        return dp[ind];
    }
}
//DP approach -
//https://leetcode.com/problems/jump-game-ii/solutions/2742894/consise-o-n-java-solution-using-recursion-and-memoization/comments/2404967 
//https://leetcode.com/problems/jump-game-ii/solutions/2742894/consise-o-n-java-solution-using-recursion-and-memoization/
//https://leetcode.com/problems/jump-game-ii/solutions/4516926/jump-game-ii/
