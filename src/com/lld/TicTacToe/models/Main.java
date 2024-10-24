//package com.lld.TicTacToe.models;
//public class Main {
// TODO
//    public static void main(String[] args) {
//        // Create a TicTacToe game instance
//        Game game = new Game();
//
//        // Initialize players with names and symbols
//        Player player1 = new Player("Player 1", 'X');
//        Player player2 = new Player("Player 2", 'O');
//
//        // Set the players in the game
//        game.setPlayers(player1, player2);
//
//        // Optionally, print the initial empty board
//        System.out.println("Initial Board:");
//        game.getBoard().printBoard();
//
//        // Simulate game play by calling the play() method
//        System.out.println("Starting game...");
//        game.play();
//
//        // Print final board after game is over
//        System.out.println("Final Board:");
//        game.getBoard().printBoard();
//
//        // Check if game is over and determine the result
//        if (game.isGameOver()) {
//            Player winner = game.getWinner();
//            if (winner != null) {
//                System.out.println("Winner: " + winner.getName());
//            } else {
//                System.out.println("It's a draw!");
//            }
//        }
//    }
//}
