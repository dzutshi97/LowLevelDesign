package com.Uber;

import java.util.*;

//LC: 269. Alien Dictionary
//TC:
public class AlienDict {

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character,Integer> indegree = new HashMap<>();

        /**
         * populate indegree as 0 initially for all chars. Note the 2 for loops
         */
        for(String word: words){
            for(Character c: word.toCharArray()){
                indegree.put(c,0);
            }
        }

        /**
         * Build adj map & list before doing topo sort
         */
        for(int i=0; i<words.length-1;i++){
            String curr= words[i];
            String next= words[i+1];
            // In a valid alphabet, prefixes are always first
            if(curr.length() > next.length() && curr.startsWith(next)){ //note && here
                return "";
            }
            int minLen = Math.min(curr.length(), next.length());
            for(int k=0; k<minLen;k++){
                char c1= curr.charAt(k);
                char c2 = next.charAt(k);
                if(c1 != c2){
                    if(!adj.containsKey(c1)){
                        adj.put(c1, new HashSet<>());
                    }
                    Set<Character> set = adj.get(c1);
                    if(!set.contains(c2)){
                        set.add(c2);
                        indegree.put(c2,indegree.get(c2)+1);
                    }
                    break;
                }
            }
        }

        /**
         * Do topo sort on all chars where the indegree == 0
         */
        Queue<Character> topoSort = new LinkedList<>();
        for(Character ch: indegree.keySet()){
            if(indegree.get(ch) == 0){
                topoSort.offer(ch);
            }
        }
        String res ="";
        while(!topoSort.isEmpty()){
            char currCh = topoSort.poll();
            res+= currCh;

            if(adj.containsKey(currCh)) {
                for (Character neigh : adj.get(currCh)) {
                    indegree.put(neigh, indegree.get(neigh) - 1); // >0 check needed? No
                    if (indegree.get(neigh) == 0) {
                        topoSort.offer(neigh);
                    }
                }
            }
        }
    return res;
    }

    public static void main(String[] args) {

        AlienDict alienDict = new AlienDict();
        String[] words = new String[]{"wrt","wrf","er","ett","rftt"}; //ans: wertf
        String ans = alienDict.alienOrder(words);
        System.out.println(ans);
    }
}
