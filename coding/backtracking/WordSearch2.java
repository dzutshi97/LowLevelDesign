package com.coding.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//Trie + Backtracking
//LC: 212. Word Search II
class TrieNode1{
    HashMap<Character, TrieNode1> children;
    boolean isWordEnd;
    String word;
    public TrieNode1(){
        children = new HashMap<>();
        isWordEnd = false;
    }
}

public class WordSearch2 {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        Solution sol = new Solution();
        List<String> ans = sol.solve(board,words);
        System.out.println(ans);
    }
}
class Solution{
    class Trie{
        TrieNode1 root;
        public Trie(){
            root = new TrieNode1();
        }
        public void addWordToTrie(String word){
            TrieNode1 node = root;
            for(char c: word.toCharArray()){
                if(!node.children.containsKey(c)){
                    node.children.put(c, new TrieNode1()); //note: don't do -> node = node.children.put(c, new TrieNode1())
                }
                node = node.children.get(c);
            }
            node.isWordEnd = true;
            node.word = word;
        }
        public  void addWordsToTrie(List<String> words){
            for(String word: words){
                addWordToTrie(word);
            }
        }
    }

    static int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1},{0,1}};
    static List<String> ans = new ArrayList<>();
    Trie trie = new Trie();

    public List<String> solve( char[][] board, String[] words){
        trie.addWordsToTrie(Arrays.asList(words));
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                search(trie.root,i,j,board);
            }
        }
        return ans;
    }

    public  void search(TrieNode1 node, int row, int col, char[][] board){ // String[] words - not needed as we stored words already in Trie!
        if(!node.children.containsKey(board[row][col])){
            return;
        }
        node = node.children.get(board[row][col]); //ADD here !!

        if(node.isWordEnd){
            ans.add(node.word);
            node.isWordEnd = false; //Is this for words with same prefixes like "app", "apps", "apple"? Here, app would be end of word and we may add it. Are we setting the flag as false so that we don't add it more than once?
        }
//        node = node.children.get(board[row][col]); NOT here !!

        char ch = board[row][col];
        board[row][col] = '#';
        for(int[] dir: dirs){
            int nRow = row + dir[0];
            int nCol = col + dir[1];
            if(nRow < 0 || nCol < 0 || nRow >= board.length || nCol >= board[0].length || board[nRow][nCol] == '#'){
                continue;
            }
            search(node,nRow,nCol,board);
        }
        board[row][col] = ch;
    }
}
