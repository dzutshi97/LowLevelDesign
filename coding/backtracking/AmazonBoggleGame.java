package com.coding.backtracking;

import java.util.HashSet;
import java.util.Set;

public class AmazonBoggleGame {
    public static void main(String[] args) {

        AmazonBoggleGame amazonBoggleGame= new AmazonBoggleGame();
        Set<String> dic= new HashSet<>();
        dic.add("DATA");dic.add("HALO");dic.add("HALT");dic.add("SAG");dic.add("BEAT");dic.add("TOTAL");dic.add("GLOT");dic.add("DAG");
        Set<String> set=amazonBoggleGame.words(new char[][]{
                {'D','A','T','H'},
                {'C','G','O','A'},
                {'S','A','T','L'},
                {'B','E','D','G'},

        },dic);
        System.out.println(set);
    }

    static int[][] directions = new int[][]{
            {0,1},
            {0,-1},
            {1,0},
            {1,-1},
            {1,1},
            {-1,0},
            {-1,1},
            {-1,-1}
    };
    public Set<String> words(char[][] matrix, Set<String> dictionary)
    {
        Set<String> res= new HashSet<>();
        for ( int i=0; i < matrix.length; i++)
        {
            for ( int j=0; j < matrix[0].length; j++)
            {
                boolean[][] visitor= new boolean[matrix.length][matrix[0].length];

                String tmp= ""+matrix[i][j];
                visitor[i][j]=true;
                visit(i, j, tmp, matrix, visitor, res, dictionary);
            }
        }
        return res;
    }

    public void visit(int i, int j, String tmp, char[][]  matrix, boolean[][] visited, Set<String> set, Set<String> dictionary)
    {
        if ( dictionary.contains(tmp))
        {
            set.add(tmp);
            return;
        }

        for ( int[] d : directions)
        {
            int new_x= i+d[0];
            int new_y= j+d[1];

            if ( isValid(new_x, new_y, matrix) && !visited[new_x][new_y])
            {
                visited[new_x][new_y]=true;
                visit(new_x,new_y, tmp+matrix[new_x][new_y], matrix, visited, set, dictionary);
                visited[new_x][new_y]=false;

            }
        }
    }

    public boolean isValid(int x, int y, char[][] matrix)
    {
        if (  x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) return false;
        return true;
    }

}
