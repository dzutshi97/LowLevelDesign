package coding.DFS;

import java.util.ArrayList;
import java.util.List;

public class RatInAMaze {
    static int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    static List<String> ans = new ArrayList<>();

    public static void solve(int[][] mat){
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        dfs(0,0,"", visited,mat);
    }

    public static void printAns(List<String> ans){
        System.out.println(ans);
    }

    private static void dfs(int row, int col, String path, boolean[][] visited, int[][] mat){
        if(row == mat.length -1 && col == mat[0].length -1){
            ans.add(path);
            System.out.println(ans);
//            printAns(ans);
            return;
        }

        for(int[] dir : dirs){
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(newRow < 0 || newCol < 0 || newRow >= mat.length || newCol >= mat[0].length || mat[newRow][newCol] == 0 || visited[newRow][newCol]){
                continue;
            }

            String dirn = new String();
            if(dir[0] == 0 && dir[1] == 1){
                dirn = dirn + "R";
            }else if(dir[0] == 0 && dir[1] == -1){
                dirn = dirn + "L";
            }
            else if(dir[0] == 1 && dir[1] == 0){
                dirn = dirn + "D";
            }
            else if(dir[0] == -1 && dir[1] == 0){
                dirn = dirn + "U";
            }

            visited[newRow][newCol] = true;
            dfs(newRow, newCol, path + dirn,visited,mat);
            visited[newRow][newCol] = false;

        }

    }

    public static void main(String[] args) {

        int[][] ip = new int[][]{{1, 0, 0, 0},
                                {1, 1, 0, 1},
                                {1, 1, 0, 0},
                                {0, 1, 1, 1}};

        //DDRDRR DRDDRR
        solve(ip);
    }
}
