package coding.DFS;

//827. Making A Large Island

//simple dfs to count the no of 1s
//only 41 TCs pass out of 75 in LC with this code. Ideal approach: Disjoint set
public class MakingALargeIsland_flaw {
    static int count =0;
    static int[][] dirs = new int[][]{{0,-1},{1,0},{-1,0},{0,1},
            {-1,1}, //upper right
            {-1,-1}, //upper left
            {1,-1}, //lower left
            {1,1}}; //lower right

    public static void main(String[] args) {

//        int[][] grid = new int[][]{
//                {0,0,1,0,0,0,0,1,0,0,0,0,0},
//                {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                {0,1,1,0,1,0,0,0,0,0,0,0,0},
//                {0,1,0,0,1,1,0,0,1,0,1,0,0},
//                {0,1,0,0,1,1,0,0,1,1,1,0,0},
//                {0,0,0,0,0,0,0,0,0,0,1,0,0},
//                {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
//        int[][] grid = new int[][]{{1,0},{0,1}}; //op: 3
//        int[][] grid = new int[][]{{1,1},{1,0}}; //op: 4
        int[][] grid = new int[][]{{1,1},{1,1}}; // op: 4
        int max_area = -999;
        int[][] originalGrid = new int[grid.length][grid[0].length];


        // Copy the original grid to restore it after each DFS call
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                originalGrid[i][j] = grid[i][j];
            }
        }

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 0){
                    grid[i][j] = 1;
                    dfs(grid, i, j);
                    max_area = Math.max(max_area, count);
                    count =0;
                    grid[i][j] = originalGrid[i][j];
                }
            }
        }
        if(max_area == -999){
            max_area = grid.length * grid[0].length;
        }
        System.out.println(max_area);

    }
     public static void dfs(int[][] grid, int row, int col){

        if(row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 1){
            grid[row][col] = 0;
            count += 1;
            for(int[] dir: dirs){
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                dfs(grid,newRow,newCol);
            }
        }
    }

}
