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

   public boolean isWinner(Player currentPlayer) {
    char symbol = currentPlayer.getSymbol();
    int n = board.board.length;

    // Check rows
    for (int row = 0; row < n; row++) {
        boolean rowWin = true;
        for (int col = 0; col < n; col++) {
            if (board.board[row][col] != symbol) {
                rowWin = false;
                break;
            }
        }
        if (rowWin) {
            isGameOver = true;
            return true;
        }
    }

    // Check columns
    for (int col = 0; col < n; col++) {
        boolean colWin = true;
        for (int row = 0; row < n; row++) {
            if (board.board[row][col] != symbol) {
                colWin = false;
                break;
            }
        }
        if (colWin) {
            isGameOver = true;
            return true;
        }
    }

    // Check main diagonal
    boolean diagWin = true;
    for (int i = 0; i < n; i++) {
        if (board.board[i][i] != symbol) {
            diagWin = false;
            break;
        }
    }
    if (diagWin) {
        isGameOver = true;
        return true;
    }

    // Check anti-diagonal
    boolean antiDiagWin = true;
    for (int i = 0; i < n; i++) {
        if (board.board[i][n - i - 1] != symbol) {
            antiDiagWin = false;
            break;
        }
    }
    if (antiDiagWin) {
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
