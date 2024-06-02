package com.lld.SudokuSolver;

public class SudokuService {
    public boolean solve(char[][] board, int BOARD_SIZE){
        if(board == null || board.length == 0)
            return false;

        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0;j<BOARD_SIZE; j++){
                if(board[i][j] == '.'){
                    for(char k='1'; k<='9' ;k++){
                        if(isValid(i,j,k,board)){
                            board[i][j] = k;
                            if(solve(board,BOARD_SIZE)){
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
                }
            }
        //imp - what if sudoku was already filled w/o any blank spaces? That is why we must be returning true here
        return true;
    }

    public boolean isValid(int i, int j, char k, char[][] board){
        int regionRow = 3 * (i/3);
        int regionCol = 3 * (j/3);
        for(int pos=0;pos<9;pos++){
            if(board[i][pos] == k){ //check in row
                return false;
            }
            if(board[pos][j] == k){ //check in col
                return false;
            }
            if(board[regionRow + pos/3][regionCol + pos%3] == k){         //check in subcross
                return false;
            }
        }
        return true;
    }

    public void printBoard(int BOARD_SIZE, char[][] board){
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
