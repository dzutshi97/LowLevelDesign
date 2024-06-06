package com.coding.backtracking;

public class WordSearch {
    static int[][] dirs = new int[][]{ {0,-1}, {0,1}, {1,0}, {-1,0}};

    public static boolean start(char[][] words, String word){
        boolean[][] visited = new boolean[words.length][words[0].length];

        for(int i=0; i<words.length; i++){
            for(int j=0; j<words[0].length; j++){
                if(words[i][j] == word.charAt(0)){
                    String wrd = ""+ words[i][j];
                    if(solve(i,j,0,wrd,word, words,visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean solve(int row, int col,int idx,  String word, String finalWord, char[][] words, boolean[][] visited){
        if(idx >= finalWord.length()){
            return false;
        }
        if(idx == finalWord.length()-1 && words[row][col] == finalWord.charAt(idx)){
            return true;
        }
        if(words[row][col] != finalWord.charAt(idx)){
            return false;
        }
        visited[row][col] = true;

        for(int[] d: dirs){
            int newRow = row  + d[0];
            int newCol = col + d[1];
            if(newRow < 0 || newCol < 0 || newRow >= words.length || newCol >= words[0].length || visited[newRow][newCol]){
                continue;
            }
            if(solve(newRow, newCol, idx+1, "", finalWord, words, visited)){
                return true;
            }
        }
        visited[row][col] = false;
        return false;
    }

    public static void main(String[] args) {

        char[][] input = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };

        String word = "ABCCED";
        boolean res = start(input,word);
        System.out.println(res);
    }
}
//leetcode - Word Search