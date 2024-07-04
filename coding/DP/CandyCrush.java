package coding.DP;

import java.util.Random;

import static coding.DP.Solve.printBoard;
import static coding.DP.Solve.solve;

/**
 * Design the Candy Crush Saga game, there are five kinds of candy, the goal is to initialize the game and the same candy will not appear three times continuously in a row or column. and the board size is 10 * 10
 * e.g. (1,2,3,4,5 mean different types of candy)
 * 1 2 2 1 2 2 3 3 5 1
 * 2 2 1 2 2 3 3 5 1 4
 * 3 3 5 1 4 5 4 1 2 2
 * 1 2 3 5 1 1 4 1 2 2
 * 4 2 3 3 5 1 2 3 4 4
 * 1 4 2 1 2 2 3 3 5 1
 * 1 2 2 1 4 1 1 2 5 1
 * 5 2 5 4 2 2 3 3 4 2
 */
 class Solve{

     public static void solve(int[][] board){
         Random r = new Random();

         //first column - first 2 cells
         for(int i=0; i<board.length; i++) {
             int prev = 0;
             int number = r.nextInt(10);
             if (i == 0 || i == 1) {
                 board[i][0] = number;
             }else { //first col, rest of the cells
                 while(board[i-1][0] == number && board[i-2][0] == number){
                     number = r.nextInt(5)+1;
                 }
                 board[i][0] = number;
             }
         }
         //first row - first 2 cells
         for(int i=0; i<board[0].length;i++){
             int prev = 0;
             int number = r.nextInt(5)+1;
             if( i== 0 || i== 1){
                 board[0][i] = number;
             }else{
                 while( board[0][i-1] == number && board[0][i-2] == number){
                    number = r.nextInt(5)+1;
                 }
                 board[0][i] = number;
//                 prev = number;
             }
         }

         for(int i=1; i<board.length; i++){
             for(int j=1; j<board[0].length; j++){

                 int number = r.nextInt(5)+1;
                 if(i >= 2){
                     while(board[i-1][j] == number && board[i-2][j] == number){
                         number = r.nextInt(5)+1;
                     }
                     board[i][j] = number;

                 }else if(j >= 2){
                     while(board[i][j-1] == number && board[i][j-2] == number){
                         number = r.nextInt(5)+1;
                     }
                     board[i][j] = number;
                 }

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
public class CandyCrush{
    public static void main(String[] args) {
        int[][] board = new int[10][10];
        solve(board);
        printBoard(board);

    }
}
