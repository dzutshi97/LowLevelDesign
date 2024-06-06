package com.coding.backtracking;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


//todo: NOTE: Same as word search 2. Just that in Word Boggle we move in 8 directions
      class TrieNode{
        public boolean isWord = false;
          HashMap<Character, TrieNode> child;
        public TrieNode() {
            this.child = new HashMap<>();
        }
    }
     class Trie1 {
        TrieNode root;

        public Trie1() {
            root = new TrieNode();
        }

        public void addToTrie(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!node.child.containsKey(ch)) {
                    node.child.put(ch,  new TrieNode());
                }
                node = node.child.get(ch);
            }
            node.isWord = true;
        }
    }


   class WordBoggle {
    public static void start(char[][] words, Set<String> dict) {
        Set<String> res = new HashSet<>(); // To store found words
        boolean[][] visited = new boolean[words.length][words[0].length];

        Trie1 trie = new Trie1();
        for(String word : dict){
            trie.addToTrie(word);
        }

        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words[0].length; j++) {
                if(trie.root.child.containsKey(words[i][j])){
                    solve(i, j, words, dict, "", visited, res, trie.root);
                }
            }
        }

        // Print all found words
        for(String word : res) {
            System.out.println(word);
        }
    }
    static int[][] dirs = new int[][]{
            {0,-1}, {0,1}, {1,0}, {-1,0},
            {1,-1}, {-1,1}, {1,1}, {-1,-1}
    };

    public static void solve(int i, int j, char[][] words, Set<String> dict, String word, boolean[][] visited, Set<String> res, TrieNode node) {
        if(visited[i][j]){
            return;
        }
        if(!node.child.containsKey(words[i][j])){
            return;
        }
        node = node.child.get(words[i][j]);
        word = word + words[i][j];
        if(node.isWord){
            res.add(word);
        }
        visited[i][j] = true;

        // Explore in all 8 directions
        for (int[] dir : dirs) {
            int nRow = i + dir[0];
            int nCol = j + dir[1];
            if (nRow < 0 || nCol < 0 || nRow >= words.length || nCol >= words[0].length) {
                continue;
            }
            solve(nRow, nCol, words, dict, word, visited, res, node);
        }
        visited[i][j] = false; // Backtrack
    }

    public static void main(String[] args) {
        char[][] input = new char[][]{
                {'D', 'A', 'T', 'H'},
                {'C', 'G', 'O', 'A'},
                {'S', 'A', 'T', 'L'},
                {'B', 'E', 'D', 'G'},
        };

        Set<String> dic = new HashSet<>();
        dic.add("DATA");
        dic.add("HALO");
        dic.add("HALT");
        dic.add("SAG");
        dic.add("BEAT");
        dic.add("TOTAL");
        dic.add("GLOT");
        dic.add("DAG");

        start(input, dic);
    }
}
//https://leetcode.com/discuss/interview-question/1036815/amazon-sde2-onsite-implement-boggle-game-trie-dfs-scalability-follow-up-question