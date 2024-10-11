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
        boolean won= true;
        //check in all rows
        for(int row = 0; row<board.board.length; row++) {
            for (int col = 0; col < board.board[0].length; col++) {
                if (board.board[row][col] != currentPlayer.getSymbol()) {
                    won = false;
                    break;
                }
            }
        }
        //check in all cols
        for(int col=0; col<board.board[0].length; col++){
            for(int row=0; row<board.board.length; row++){
                if(board.board[row][col] != currentPlayer.getSymbol()){
                    won = false;
                    break;
                }
            }
        }
        //check in daig
        //check in cross-diag
        for(int col=0; col<board.board[0].length; col++){
            for(int row=0; row<board.board.length; row++){
                if(row == col){
                    if(board.board[row][col] != currentPlayer.getSymbol()){
                        won = false;
                        break;
                    }
                }
            }
        }
        if(won){
            isGameOver = true;
            return true;
        }
        return false;
        /** OR follow this approach of early exit by using 4 flags -
         *    // Check the main diagonal
         *     boolean diag1Win = true;
         *     for (int i = 0; i < size; i++) {
         *         if (board[i][i] != symbol) {
         *             diag1Win = false;
         *             break;
         *         }
         *     }
         *     if (diag1Win) return true;
         *
         *     // Check the anti-diagonal (from top-right to bottom-left)
         *     boolean diag2Win = true;
         *     for (int i = 0; i < size; i++) {
         *         if (board[i][size - i - 1] != symbol) { UNDERSTAND THIS
         *             diag2Win = false;
         *             break;
         *         }
         *     }
         *     if (diag2Win) return true;
         */
    }

    public void switchPlayer(){
        if(players.get(0) == currentPlayer){
            this.currentPlayer = players.get(1);
        }else {
            this.currentPlayer = players.get(0);
        }
    }

    public void play(){

        while(isGameOver){
            //current player will make a move
            MoveStrategy humanMoveStrategy = new HumanMoveStrategy();
            currentPlayer.makeNextMove(humanMoveStrategy);
                    //check if he is the winner. Yes, then set game over
            if(isWinner(currentPlayer)){
                return;
            }
            //if not, switch the player
            switchPlayer();
        }
    }
}
