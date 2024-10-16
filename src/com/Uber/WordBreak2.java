package com.Uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * TC:
 * The recursive function attempts all possible partitions of the string, which can lead to exponential growth in the number of recursive calls.
 * In the worst case, every partition could be a valid word, leading to an exponential number of solutions.
 * This part contributes O(2^n) in the worst case, where n is the length of the string, as you explore all possible substring combinations.
 *
 * Final Time Complexity:
 * Thus, the total time complexity in the worst case is:
 *
 * O(n * 2^n), where n is the length of the string s.
 *
 * TODO: Optimization: memoize the below solution
 */
public class WordBreak2 {
    List<String> answer = new ArrayList<>();

    public void solve(String s, String[] wordDict){
        HashSet<String> wordSet = new HashSet<>();
        wordSet.addAll(Arrays.asList(wordDict));
        dfs(0,s,wordSet,"");

        for(String ans: answer){
            System.out.println(ans);
        }
    }

    public void dfs(int idx, String s, HashSet<String> wordSet, String ans){
        if(idx == s.length()){ //note: no -1 here
            answer.add(ans);
        }
        for(int i=idx; i<s.length(); i++){
            String sub = s.substring(idx,i+1); //note: +1 here
            if(wordSet.contains(sub)){
                if(ans.isEmpty()){
                    dfs(i+1,s,wordSet, ans + sub); //first word
                }else{
                    dfs(i+1,s,wordSet, ans + " " + sub);
                }
            }
        }
    }
    public static void main(String[] args) {

        String[] wordDict = new String[]{"cat","cats","and","sand","dog"};
        String s = "catsanddog";
        //Expected ans: ["cats and dog","cat sand dog"]

        WordBreak2 wordBreak2 = new WordBreak2();
        wordBreak2.solve(s,wordDict);
    }
}
