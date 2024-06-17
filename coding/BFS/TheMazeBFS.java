package coding.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class TheMazeBFS {

    static int[][] dirs = new int[][]{{0,-1}, {0,1}, {1,0}, {-1,0}};

    static Queue<int[]> q = new LinkedList<>();
    public static boolean start(int row, int col, int[][] maze, int[] start, int[] dest){
        q.offer(new int[]{start[0], start[1]});
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        return solve(q,maze,dest,visited);
    }

    public static boolean solve(Queue<int[]> q, int[][] maze, int[] dest, boolean[][] visited){

        while(!q.isEmpty()){
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];

            if(r == dest[0] && c == dest[1]){
                return true;
            }
//  can't do this in BFS !!
//            if(visited[r][c]){
//                continue;
//            }

            visited[r][c] = true;

            for(int[] d: dirs){
                int nRow = r;
                int nCol = c;

                while(nRow + d[0] >= 0 && nCol + d[1] >= 0 && nRow + d[0] < maze.length && nCol + d[1] < maze[0].length && maze[nRow + d[0]][nCol + d[1]] == 0
                        ){ //&& !visited[nRow + d[0]][nCol + d[1]] - this condition is not needed !!!!
                    nRow = nRow + d[0];
                    nCol = nCol + d[1];
                }
                if(!visited[nRow][nCol]){
                    q.offer(new int[]{nRow, nCol});
                }
            }
        }
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
//        int[] dest = new int[]{3,2}; //false
        int[] dest = new int[]{4,4}; //true

        boolean ans = start(start[0], start[1],maze, start, dest);
        System.out.println(ans);

    }
}
