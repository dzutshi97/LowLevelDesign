package com.Uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class TrieNode3{

    HashMap<Character,TrieNode3> children;
    boolean isWordEnd;
    String word;

    public TrieNode3() {
        this.children = new HashMap<>();
        this.isWordEnd = false;
        this.word = "";
    }
}
class Trie3{
    TrieNode3 root;
    public Trie3() {
        this.root = new TrieNode3();
    }

    public void insertToTrie(String word){

        char[] wordCh = word.toCharArray();
        TrieNode3 node = root;
        for(int i=0; i<wordCh.length; i++){
            if(!node.children.containsKey(wordCh[i])){
                node.children.put(wordCh[i], new TrieNode3());
            }
            node = node.children.get(wordCh[i]);
        }
        node.isWordEnd = true;
        node.word = word;
    }
}
public class WordSearch2 {

    Trie3 trie3 = new Trie3();
    List<String> ans = new ArrayList<>();

    public void solve(char[][] input, String[] words){
        for(String word: words){
            trie3.insertToTrie(word);
        }

        for(int i=0; i<input.length; i++){
            for(int j=0; j<input[0].length; j++){
                search(trie3.root,i,j,input); //explore from each cell
            }
        }

        for(int i=0; i<ans.size(); i++){
            System.out.println(ans.get(i)+ " ");
        }
    }

    int[][] dirs = new int[][]{{0,1},{-1,0},{1,0},{0,-1}};

    public void search(TrieNode3 node, int row, int col, char[][] board){ // String[] words - not needed as we stored words already in Trie!
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

    public static void main(String[] args) {

        WordSearch2 wordSearch2 = new WordSearch2();
        char[][] input =
                {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        wordSearch2.solve(input,words);
    }
}
