package com.Uber.TrieQuestion;
import java.util.HashMap;



 class TrieNode{
    HashMap<Character, TrieNode> childern;
    boolean isWord;

    public TrieNode() {
        this.childern = new HashMap<>();
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        TrieNode current = root;
        for (int i=0; i<s.length(); i++) {
            if (!current.childern.containsKey(s.charAt(i))) {
                current.childern.put(s.charAt(i), new TrieNode());
            }
            current = current.childern.get(s.charAt(i));
        }
        current.isWord = true;
    }

    public int isPrefix(String s) {
        TrieNode node = root;
        int idx = -1;
        char [] ch= s.toCharArray();
        for (int i=0; i< ch.length; i++) {
            Character c = s.charAt(i);
            if (node.childern.containsKey(c)) {
                node = node.childern.get(c);
                idx = i; //magic line for this question.
            }
        }
        if (node.isWord) {
            return idx;
        }
        return idx;
    }
}

class TrieQuestion {

     public String solution(String input, String[] words){
         Trie trie = new Trie();
         for(String word: words){
             trie.insert(word);
         }

         String[] inp = input.split(" ");
         String res = "";
         for(String s: inp){
             int idx = trie.isPrefix(s);
             if(idx > 0){
                 res = res + " " + "[" + s.substring(0,idx + 1) + "]" + s.substring(idx+1, s.length());
             }else {
                 res = res + " " + s;
             }
         }
         return res;
     }


    public static void main(String[] args) {
        String input = "Hunter Joe BidenBi";
        String[] words = new String[]{"H", "He", "Hun", "B" , "Bi", "Bay"};
        TrieQuestion trieQuestion = new TrieQuestion();
        String ans = trieQuestion.solution(input,words);
        System.out.println(ans);

    }
}
//https://leetcode.com/discuss/interview-question/1991675/Uber-or-Onsite-or-2022-or-Rejected-or-India
