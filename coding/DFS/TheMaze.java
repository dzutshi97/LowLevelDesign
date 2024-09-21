package coding.DFS;

//TODO: BFS approach. Soln - https://leetcode.com/problems/the-maze/solutions/1141843/java-readable-dfs-explanation-o-nm-time-bonus-bfs/
// BFS soln coded - https://github.com/dzutshi97/LowLevelDesign/blob/main/coding/BFS/TheMazeBFS.java
public class TheMaze {
    static int[][] dirs = new int[][]{{0,-1}, {0,1}, {1,0}, {-1,0}};

//    public static boolean isValid(int row, int col, int[][] maze, boolean[][] visited){
//
//        if(row<0 || col<0 || row >= maze.length || col >= maze[0].length || maze[row][col] == 1 || visited[row][col]){
//            return false;
//        }
//        return true;
//    }

    public static boolean solve(int row, int col, int[][] maze, int[] start, int[] dest, boolean[][] visited){

        if(row == dest[0] && col == dest[1]){
            return true;
        }

        if(visited[row][col]){
            return false;
        }

        visited[row][col] = true;

        for(int[] d : dirs){
            int nRow = row;//row + d[0];
            int nCol = col;//col + d[1];

//            while(isValid(nRow,nCol,maze,visited) && nRow >= 0 && nCol >= 0 && nRow < maze.length && nCol < maze[0].length && maze[nRow][nCol] == 0){
//                nRow = nRow + d[0];
//                nCol = nCol + d[1];
//            }
//            if(!isValid(nRow,nCol,maze,visited)){
//                return false;
//            }
            while(nRow + d[0] >= 0 && nCol + d[1] >= 0 && nRow + d[0] < maze.length && nCol + d[1] < maze[0].length && maze[nRow + d[0]][nCol + d[1]] == 0
                    && !visited[nRow + d[0]][nCol + d[1]]){ //&& !visited[nRow + d[0]][nCol + d[1]] - !!!! this condition is not needed though. Will not work with BFS!!!
                nRow = nRow + d[0];
                nCol = nCol + d[1];
            }

            if(solve(nRow, nCol,maze,start,dest,visited)){
                return true;
            }
        }
        //we do not have to backtrack. imagine the question grid movement.
        return false;
    }

    public static void main(String[] args) {

        int[][] maze = new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0},
        };
        int[] start = new int[]{0,4};
//        int[] dest = new int[]{3,2};
        int[] dest = new int[]{4,4};

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        boolean ans = solve(start[0], start[1],maze, start, dest, visited);
        System.out.println(ans);

    }
}
//should we do backtracking here? - I dont think so
