//package com.Uber.TrieQuestion;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//
//class TrieNode {
//    boolean isEnd;
//    HashMap<Character, TrieNode> children;
//
//    public TrieNode() {
//        isEnd = false;
//        children = new HashMap<>();
//    }
//}
//
//class Trie {
//    TrieNode root;
//    public Trie() {
//        root = new TrieNode();
//    }
//
//    public void insert(String s) {
//        TrieNode current = root;
//        for (int i=0; i<s.length(); i++) {
//            if (!current.children.containsKey(s.charAt(i))) {
//                current.children.put(s.charAt(i), new TrieNode());
//            }
//            current = current.children.get(s.charAt(i));
//        }
//        current.isEnd = true;
//    }
//
//    public boolean isPrefixPresent(String s) {
//        TrieNode current = root;
//        for (int i=0; i<s.length(); i++) {
//            if (!current.children.containsKey(s.charAt(i))) {
//                return false;
//            }
//            current = current.children.get(s.charAt(i));
//        }
//        return true;
//    }
//
//    public boolean isPresent(String s) {
//        TrieNode current = root;
//        for (int i=0; i<s.length(); i++) {
//            if (!current.children.containsKey(s.charAt(i))) {
//                return false;
//            }
//            current = current.children.get(s.charAt(i));
//        }
//        return current.isEnd;
//    }
//}
//
//class Solution {
//    public String uberQuestion(String input, String[] words) {
//        Trie myTrie = new Trie();
//        for (int i=0; i<words.length; i++) {
//            myTrie.insert(words[i]);
//        }
//        HashMap<String, Boolean> isUsed = new HashMap<>();
//
//        int s = 0, e = 0;
//        while (e+1<input.length()) {
//            while(e+1<=input.length() && myTrie.isPrefixPresent(input.substring(s, e+1))) {
//                e = Math.min(e+1, input.length());
//            }
//            if (myTrie.isPresent(input.substring(s, e)) && !isUsed.containsKey(input.substring(s, e))) {
//                isUsed.put(input.substring(s,e), true);
//                input = input.substring(0, s) + "[" + input.substring(s, e) + "]" +
//                        input.substring(e, input.length());
//                e = Math.min(e+2, input.length());
//            }
//            e++;
//            s = e;
//        }
//        return input;
//    }
//
//    public static void main(String[] args) {
//        Solution sol = new Solution();
//        String input = "Hunter Joe BidenBi";
//        String[] words = new String[] {"H", "He", "Hun", "B" , "Bi", "Bay"};
//        String answer = sol.uberQuestion(input, words);
//        System.out.println(answer);
//    }
//}