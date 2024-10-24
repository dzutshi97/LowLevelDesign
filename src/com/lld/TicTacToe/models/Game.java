package com.lld.TicTacToe.models;

import java.util.List;

public class Game {

    List<Player> players;
    Board board;
    Player currentPlayer;
    boolean isGameOver;

    public Game(List<Player> players, Board board, Player currentPlayer) {
        this.players = players;
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public boolean isWinner(Player currentPlayer){
        //logic to check for winner in row, col, diag & cross diagonal
        boolean rowWin= true;
        //check in all rows
        for(int row = 0; row<board.board.length; row++) {
            for (int col = 0; col < board.board[0].length; col++) {
                if (board.board[row][col] != currentPlayer.getSymbol()) {
                    rowWin = false;
                    break;
                }
            }
        }
        if(rowWin){
            isGameOver = true;
            return true;
        }
        //check in all cols
        boolean colWin = true;
        for(int col=0; col<board.board[0].length; col++){
            for(int row=0; row<board.board.length; row++){
                if(board.board[row][col] != currentPlayer.getSymbol()){
                    colWin = false;
                    break;
                }
            }
        }
        if(colWin){
            isGameOver = true;
            return true;
        }
        //check in daig
        boolean diagWon = true;
        for(int i=0; i<board.board.length; i++){
            if(board.board[i][i] != currentPlayer.getSymbol()){
                diagWon = false;
                break;
            }
        }
        if(diagWon){
            isGameOver = true;
            return true;
        }

        //check in cross-diag
        boolean crossDiagWon= true;
        for(int i=0; i<board.board.length; i++){
            if(board.board[i][board.board.length - i - 1] != currentPlayer.getSymbol()){
                crossDiagWon = false;
                break;
            }
        }
        if(crossDiagWon){
            isGameOver = true;
            return true;
        }
        return false;
    }

    public void switchPlayer(){
        if(players.get(0) == currentPlayer){
            this.currentPlayer = players.get(1);
        }else {
            this.currentPlayer = players.get(0);
        }
    }

    public void play(){

        while(!isGameOver){
            //current player will make a move
            MoveStrategy humanMoveStrategy = new HumanMoveStrategy();
            int[] pos = currentPlayer.makeNextMove(humanMoveStrategy);
            int r = pos[0];
            int c = pos[1];
            board.board[r][c] = currentPlayer.getSymbol();
                    //check if he is the winner. Yes, then set game over
            if(isWinner(currentPlayer)){
                return;
            }

            // Check if the board is full (resulting in a draw)
            if (isBoardFull()) {
                System.out.println("The game is a draw!");
                isGameOver = true;
                return;
            }
            //if not, switch the player
            switchPlayer();
        }
    }
    private boolean isBoardFull() {
        for (int i = 0; i < board.board.length; i++) {
            for (int j = 0; j < board.board[i].length; j++) {
                if (board.board[i][j] == '-') {  // Assuming empty cells are represented by '-'
                    return false;  // There is still space on the board
                }
            }
        }
        return true;  // No empty spaces left
    }
}
