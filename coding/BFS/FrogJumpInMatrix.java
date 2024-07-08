package coding.BFS;

import java.util.*;
//     * Link - https://leetcode.com/discuss/interview-question/518916/google-onsite-frog-in-a-matrix
// ~= Similar to https://www.geeksforgeeks.org/minimum-steps-reach-target-knight/ (Uber) !!
public class FrogJumpInMatrix {
    /**
     * Given a 2d array with size n*m, there's some obstacles placed at some points. A frog trying to jump from the beginning point 0,0 to the bottom right point n-1, m-1. Say if a frog can jump maximum k distance a time, what is the minimun steps needed?
     * Note: the frog can only jump horizontally or vertically, it can't jump diagonally. Obstacles are represented by -1.
     *
     * eg:
     * [0,0,-1,0,0,0,
     * -1,0,0,0,-1,0], maximum step k = 2
     *
     * answer: 6
     */
    static int[][] dirs = {{-1,0}, {1,0}, {0,1},{0,-1}};
    public static int solve(int[][] grid, int k){
        int n = grid.length;
        int m = grid[0].length;
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0,0});
        visited.add("0:0");
        int steps = 0;

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];
                if(row == n-1 && col == m-1){
                    return steps;
                }
                for(int[] dir: dirs){
                    for(int j=1; j<= k; j++) {
                        int newRow = row + dir[0] * j;
                        int newCol = col + dir[1] * j;
                        if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m ) {
                            continue;
                        }
                        if( grid[newRow][newCol] == -1){
                            break; //note
                            /**
                             * The break statement is necessary in this context because once the frog encounters
                             * an obstacle (a cell with a value of -1), it should stop trying to continue in that direction any further within the current jump sequence.
                             * If we used continue instead of break, the frog would incorrectly try to
                             * continue jumping further in the same direction even after encountering an obstacle, leading to incorrect behavior
                             */
                        }
                        if(visited.contains(newRow + ":" + newCol)){
                            continue;
                        }
                        visited.add(newRow + ":" + newCol);
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
            ++steps;
        }
    return -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,-1,0,0,0,},
                {-1,0,0,0,-1,0}};
        int k = 2;
        //answer: 6
        int ans = solve(grid,k);
        System.out.println(ans);
    }
}
