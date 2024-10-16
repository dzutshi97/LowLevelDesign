package com.Uber;

// https://leetcode.com/discuss/interview-question/374697/uber-phone-screen-tournament
public class TournamentWinner {

    public static int tournament(int[] arr, int k) {
        int winner = arr[0];  // Start by assuming the first player is the current winner
        int wins = 0;         // No wins initially
        for (int i = 1; i < arr.length; i++) {  // Iterate through the rest of the players
            if (arr[i] > winner) {  // If the next player is stronger
                winner = arr[i];    // Update the winner to the new player
                wins = 1;           // Reset consecutive wins to 1 (since new winner has 1 win)
            } else {                // If the current winner beats the next player
                wins++;             // Increment the consecutive wins count
            }
            if (wins == k) return winner;  // If a player wins k consecutive matches, return that player
        }
        return winner;  // If no player wins k consecutive matches, return the final winner after linear traversal
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 3, 4, 5};
        int k=2;
        System.out.println(tournament(arr,k));
    }
}
