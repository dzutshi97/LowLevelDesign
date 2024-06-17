package coding.DFS;

import java.util.HashSet;
import java.util.Set;

public class NQueens {
    public static void solve(int n){


        char[][] board = new char[n+1][n+1];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] = '.';
            }
        }
        dfs(0,n, board);

//        for(int i=0; i<board.length; i++){
//            for(int j=0; j<board[0].length; j++){
//                System.out.print(board[i][j]+ " ");
//            }
//            System.out.println();
//        }
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    static Set<Integer> diagonalVisited = new HashSet<>();
    static Set<Integer> crossDiagonalVisited = new HashSet<>();
    static Set<Integer> colVisited = new HashSet<>();

    private static void dfs(int row, int n,char[][] board){
        if(row == n) {
            printBoard(board);
            return;
        }
        for(int col = 0; col <n; col++){
            int diag = row - col;
            int crossDiag = row + col;
            if(row > n || diagonalVisited.contains(diag) || crossDiagonalVisited.contains(crossDiag) || colVisited.contains(col)){
                continue;
            }
            board[row][col] = 'Q';
            diagonalVisited.add(diag);
            crossDiagonalVisited.add(crossDiag);
            colVisited.add(col);

            dfs(row +1,n, board);

            board[row][col] = '.';
            diagonalVisited.remove(diag);
            crossDiagonalVisited.remove(crossDiag);
            colVisited.remove(col);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        solve(n);
    }
}
