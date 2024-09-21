package coding.Atlassian;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordSearchWithCoords {

    static int[][] dirs = new int[][]{{1,0}, {0,1}}; //bottom & right
    static Set<int[]> set = new HashSet<>(); //co-ords

    public static boolean solve(char[][] grid, String input){

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){

                if(grid[i][j] == input.charAt(0)){
                    return dfs(0,input,grid,i,j,set);
                }
            }
        }
        return false;
    }

    public static boolean dfs(int index, String input, char[][] grid, int row, int col, Set<int[]> set){

        char c = input.charAt(index);
        if(grid[row][col] != c){
            return false;
        }

        if(index == input.length() -1){
            set.add(new int[]{row, col}); //last co-ordinate
            return true;
        }

        char ch = grid[row][col];
        grid[row][col] = '#';
        set.add(new int[]{row, col});
        for(int[] dir: dirs){
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(newRow<0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length){
                continue;
            }
            if(dfs(index + 1, input, grid,newRow, newCol, set)){
                return true;
            }
        }
        //backtrack
        set.remove(new int[]{row, col});
        grid[row][col] = ch;
        return false;
    }
    public static void main(String[] args) {

        char[][] grid = {
                {'C', 'A', 'T'},
                {'D', 'O', 'G'},
                {'R', 'A', 'T'}
        };

        String word = "DOG";
        solve(grid,word);
        // Print the set using Arrays.toString() for each coordinate
        for (int[] coord : set) {
            System.out.println(Arrays.toString(coord));
        }    }
}
