class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> hset = new HashSet<>(wordDict);
        Boolean[] dp = new Boolean[s.length()];
        return solve(s,hset,0,dp);

    }  

    public boolean solve(String s, HashSet hset, int i,Boolean[] dp){
        if(i==s.length()){
            return true;
        }
        if(dp[i]!=null){
            return dp[i];
        }

        for(int k=i; k<s.length();k++){
            if(hset.contains(s.substring(i,k+1))){
                if(solve(s,hset,k+1,dp)){
                    return dp[i] = true;
                }
            }

        }
        return dp[i] = false;
    }
}
//https://leetcode.com/problems/word-break/solutions/3861407/memoization-dp-java-simple/
