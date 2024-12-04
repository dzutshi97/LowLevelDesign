class Solution {
    int[][] dirs = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    Queue<int[]> q = new LinkedList<>();

    public int shortestBridge(int[][] grid) {
        boolean flag= false;

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1 && !flag){
                    dfs(i,j,grid);
                    flag= true;
                }
            }
        } //note this logic for all test cases to PASS

        // dfs(0,0,grid);
        int count = 0;
        while(!q.isEmpty()){

            int size = q.size();
            for(int i=0; i<size; i++){
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];
                for(int[] d : dirs){
                    int newRow = row + d[0];
                    int newCol = col + d[1];
                    if(newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length){
                        continue;
                    }
                    if(grid[newRow][newCol] == 0){
                        q.offer(new int[]{newRow, newCol});
                        grid[newRow][newCol] = -1;  //note 
                    }else if(grid[newRow][newCol] == 1){
                        return count;
                    }
                }
            }
            count++;
        }
        return count;
    }

    public void dfs(int row, int col, int[][] grid){
        if(grid[row][col] == 1){
            grid[row][col] = -1;
        }
        q.offer(new int[]{row,col});
        for(int[] d: dirs){
            int newRow = row + d[0];
            int newCol = col + d[1];
            if(newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length){
                continue;
            }
            if(grid[newRow][newCol] == 0 || grid[newRow][newCol] == -1){
                continue;
            }
            dfs(newRow, newCol,grid);
        }
    }
}
//https://leetcode.com/problems/shortest-bridge/solutions/4222483/java-bfs-dfs-solution/
