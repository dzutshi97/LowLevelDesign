package coding.DP;

import java.util.Arrays;
import java.util.HashSet;

public class WordBreak {

    private boolean solve(int idx,String s,HashSet<String> wordSet,Boolean[] dp){
        if(idx >= s.length()){
            return true;
        }
        if(dp[idx]!=null){
            return dp[idx];
        }

        for(int i=idx; i<=s.length(); i++){
             String sub = s.substring(idx,i);
             if(wordSet.contains(sub)) {
                 if(solve(i, s, wordSet,dp)) {
                     return dp[idx] = true; //todo: understand why idx?
                 }
             }
        }
        return dp[idx] = false;
    }

    public static void main(String[] args) {
        String[] wordDict = new String[]{"leet", "code"};
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(wordDict));
        WordBreak wordBreak = new WordBreak();
        String s = "leetcode";
        Boolean[] dp = new Boolean[s.length()];
        boolean res = wordBreak.solve(0,s,wordSet,dp);
        System.out.println(res);

    }
}
/**
 * TC: O(N^2)
 */