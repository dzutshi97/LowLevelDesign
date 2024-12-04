public class Solution {

    private int n;
    private int m;
    private final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // down, up, right, left

    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
            }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int row, int col) {
        // Set current cell to '0' to mark it as visited
        // grid[row][col] = '0';

        // Traverse in all four directions using the dirs array
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the new position is valid and is a '1' (part of the island)
            if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m && grid[newRow][newCol] == '1') {
                grid[newRow][newCol] = '0';
                DFSMarking(grid, newRow, newCol);  // Recursively call DFS for the new position
            }
        }
        return; // <-- not needed!!!!
        /**
        the recursive DFS process will terminate naturally when all neighboring 1 cells are visited (or if no valid neighbor exists), so there's no need for an explicit return statement. This approach is typical for DFS or BFS traversal algorithms where the main goal is to mark or explore nodes, not return specific results from the recursion. */
    }
}
