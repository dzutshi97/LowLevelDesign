package com.Uber;

/**
 * LC: ~= 2134. Minimum Swaps to Group All 1's Together II
 * Phone screen question - Given a n x n matrix with 0s and 1s, given the min number of swaps (not necessary to be adjacent) required to created a y x y (y<=n) matrix of 1s inside.
 * https://leetcode.com/discuss/interview-question/5833928/Uber-L4-or-Interview-Process
 */
public class MatrixSwaps {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1},
                {0, 1, 1},
                {1, 1, 0}
        };
        System.out.println("Min swaps: " + minSwapsToFormMatrix(matrix));
    }

    public static int minSwapsToFormMatrix(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Count total number of 1s
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    totalOnes++;
                }
            }
        }

        // Step 2: Calculate largest possible y x y submatrix of 1s
        int y = (int) Math.floor(Math.sqrt(totalOnes));

        // Step 3: Sliding window over all possible y x y submatrices to find min swaps
        int minSwaps = Integer.MAX_VALUE;
        for (int i = 0; i <= n - y; i++) {
            for (int j = 0; j <= n - y; j++) {
                // Check how many 1s are in the current y x y submatrix
                int onesInSubmatrix = countOnes(matrix, i, j, y);

                // Swaps needed to fill this y x y submatrix with 1s
                int missingOnes = (y * y) - onesInSubmatrix;

                // Calculate minimum swaps
                minSwaps = Math.min(minSwaps, missingOnes);
            }
        }

        // Step 4: Return the minimum number of swaps found
        return minSwaps == Integer.MAX_VALUE ? -1 : minSwaps;
    }

    // Helper function to count how many 1s are in a y x y submatrix
    private static int countOnes(int[][] matrix, int row, int col, int size) {
        int count = 0;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
