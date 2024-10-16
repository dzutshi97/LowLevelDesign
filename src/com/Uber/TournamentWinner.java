package com.Uber;

/**
 * Given an array arr of unique integers [1..n]. The array represents n different players at a tournament and their overall strength, and the first two slots in the array represent the current match. The stronger player always wins and the weaker player is moved to the end of the array. The tournament ends when a player wins k matches in a row. Find the winner.
 *
 *
 * Example:
 *
 *
 * Input: arr = [2, 1, 3, 4, 5], k = 2
 * Output: 5
 * Explanation:
 * [2 1] 3 4 5 | player 2 vs. player 1, player 2 wins and player 1 is moved to the end
 * [2 3] 4 5 1 | player 2 vs. player 3, player 3 wins
 * [3 4] 5 1 2 | player 4 wins
 * [4 5] 1 2 3 | player 5 wins
 * [5 1] 2 3 4 | player 5 wins again, that's k times in a row so 5 is the overall winner
 * Expected O(n) time and O(1) space solution. arr is immutable.
 * https://leetcode.com/discuss/interview-question/374697/uber-phone-screen-tournament
 */

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
