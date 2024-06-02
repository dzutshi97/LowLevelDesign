package coding.DP;

import java.util.Random;

class Solve1 {

    public static void solve(int[][] board) {
        Random r = new Random();

        // Initialize the first two cells of the first column
        for (int i = 0; i < 10; i++) {
            int number = r.nextInt(5) + 1;
            if (i == 0 || i == 1) {
                board[i][0] = number;
            } else { // Ensure no three consecutive same numbers in the first column
                while (board[i - 1][0] == number && board[i - 2][0] == number) {
                    number = r.nextInt(5) + 1;
                }
                board[i][0] = number;
            }
        }

        // Initialize the first two cells of the first row
        for (int j = 0; j < 10; j++) {
            int number = r.nextInt(5) + 1;
            if (j == 0 || j == 1) {
                board[0][j] = number;
            } else { // Ensure no three consecutive same numbers in the first row
                while (board[0][j - 1] == number && board[0][j - 2] == number) {
                    number = r.nextInt(5) + 1;
                }
                board[0][j] = number;
            }
        }

        // Initialize the rest of the board
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                int number = r.nextInt(5) + 1;
                while ((i >= 2 && board[i - 1][j] == number && board[i - 2][j] == number) ||
                        (j >= 2 && board[i][j - 1] == number && board[i][j - 2] == number)) {
                    number = r.nextInt(5) + 1;
                }
                board[i][j] = number;
            }
        }
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Crush {
    public static void main(String[] args) {
        int[][] board = new int[10][10];
        Solve1.solve(board);
        Solve1.printBoard(board);
    }
}
