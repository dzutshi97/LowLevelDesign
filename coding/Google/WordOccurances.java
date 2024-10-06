package coding.Google;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * Given a 2D array of characters, find the number of occurrences of a given search word. The word can be formed by moving up, down, left, right, and around 90-degree bends. You cannot reuse any cell while forming the word.
 *
 * N = 4, M = 5, target = SNAKES, grid =
 * {{S,N,B,S,N},
 * {B,A,K,E,A},
 * {B,K,B,B,K},
 * {S,E,B,S,E}}
 * Output: 3
 *
 * https://www.notion.so/Google2-d4ac68ff7dfa45f5ae53caf8a0fc78e2
 */
public class WordOccurances {
    static int occurance=0;

    public static void main(String[] args) {

        String input = "SNAKE";
        char[][] grid = {
                {'S','N','B','S','N'},
                {'B','A','K','E','A'},
                {'B','K','B','B','K'},
                {'S','E','B','S','E'}
        };

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length;j++){
                if(grid[i][j] == 'S'){
                    dfs(0,i,j,grid,input);
                }
            }
        }
        System.out.println(occurance);

    }

    static int[][] dirs= new int[][]{{1,0}, {-1,0}, {0,-1}, {0,1}};
    static HashSet<String> visitedCords = new HashSet<>();
    static HashSet<String> finalCoords = new HashSet<>();

    public static void dfs(int idx, int row, int col, char[][] grid, String input){
        if(idx == input.length()-1){
            if(grid[row][col] == input.charAt(input.length()-1)){
                occurance++;
                finalCoords.addAll(new ArrayList<>(visitedCords));
                return;
            }
        }

        if(grid[row][col] != input.charAt(idx)){
            return;
        }
        visitedCords.add(row + "-" + col);
        char ch = grid[row][col];
        grid[row][col] = '#';
        for(int[] dir: dirs){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow < 0 || nCol < 0 || nRow >= grid.length || nCol > grid[0].length-1 || grid[nRow][nCol] == '#' || finalCoords.contains(nRow + "-" + nCol)
                    ||visitedCords.contains(nRow + "-" + nCol)){
                continue;
            }
            dfs(idx+1,nRow,nCol,grid, input);
        }
        grid[row][col] = ch;
        visitedCords.remove(row + "-" + col);
    }
}
