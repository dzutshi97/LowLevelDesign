package coding.BFS;

import java.util.*;

/**
 * DFS soln is in DFS folder
 *
 * Time Complexity:
 * The time complexity is proportional to the number of cells in the matrix, i.e., O(n * m), where n is the number of rows and m is the number of columns.
 * This is because each cell is processed at most once during the BFS traversal.
 * Space Complexity:
 * The space complexity is also O(n * m) due to the visited array and the queue used for BFS traversal.
 *
 * Backtracking: In BFS, backtracking is implicitly handled by the queue because we explore all possible paths level by level.
 * Once a path is complete (reaches the destination), it's added to the result, and the search continues for other paths.
 */
public class RatInAMazeBFS {
    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static List<String> ans = new ArrayList<>();

    // Solve the maze using BFS
    public static void solve(int[][] mat) {
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        bfs(0, 0, "", visited, mat);
    }

    // Function to print the final answers
    public static void printAns(List<String> ans) {
        System.out.println(ans);
    }

    // BFS function to explore the maze
    private static void bfs(int startRow, int startCol, String path, boolean[][] visited, int[][] mat) {
        Queue<MazeNode> queue = new LinkedList<>();
        queue.add(new MazeNode(startRow, startCol, path));
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            MazeNode node = queue.poll();
            int row = node.row;
            int col = node.col;
            String currentPath = node.path;

            // Check if we reached the destination
            if (row == mat.length - 1 && col == mat[0].length - 1) {
                ans.add(currentPath);
                continue; // Continue to explore other possible paths
            }

            // Explore in all 4 directions (right, left, down, up)
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the new position is valid
                if (newRow >= 0 && newCol >= 0 && newRow < mat.length && newCol < mat[0].length &&
                        mat[newRow][newCol] == 1 && !visited[newRow][newCol]) {

                    String dirn = "";
                    if (dir[0] == 0 && dir[1] == 1) {
                        dirn = "R";
                    } else if (dir[0] == 0 && dir[1] == -1) {
                        dirn = "L";
                    } else if (dir[0] == 1 && dir[1] == 0) {
                        dirn = "D";
                    } else if (dir[0] == -1 && dir[1] == 0) {
                        dirn = "U";
                    }

                    // Mark the cell as visited and add the new node to the queue
                    visited[newRow][newCol] = true;
                    queue.add(new MazeNode(newRow, newCol, currentPath + dirn));
                }
            }
        }

        // Print the result paths found
        printAns(ans);
    }

    public static void main(String[] args) {
        int[][] ip = new int[][]{{1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}};
        // Expected output: DDRDRR, DRDDRR
        solve(ip);
    }
}

// Helper class to store a node in the queue
class MazeNode {
    int row, col;
    String path;

    public MazeNode(int row, int col, String path) {
        this.row = row;
        this.col = col;
        this.path = path;
    }
}
