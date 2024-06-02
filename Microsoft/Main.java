package com.lld.Microsoft;
import java.util.*;

public class Main {

    // For storing priority of each character
    static HashMap<Character, Integer> mp = new HashMap<>();

    // Function to print the new sorted array of strings
    static void printSorted(String[] words, String order) {

        // Mapping each character to its occurrence position
        for (int i = 0; i < order.length(); i++)
            mp.put(order.charAt(i), i);

        // Sorting with custom sort function using Comparator.comparingInt
        Arrays.sort(words, (a, b) -> {
            for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
                int compare = Integer.compare(mp.get(a.charAt(i)), mp.get(b.charAt(i)));
                if (compare != 0) { //In the context of comparing two integers using the compare method, returning 0 signifies that the two integers being compared are equal.
                    return compare;
                }
                /*
                 * Less than 0 (-1): Indicates that the character in the first string (a) at the current position is lexicographically less than the character in the second string (b) at the same position.
                 * Greater than 0 (1): Indicates that the character in the first string (a) at the current position is lexicographically greater than the character in the second string (b) at the same position.
                 */
            }
              /* When loop breaks without returning, it
            means the prefix of both words were same
            till the execution of the loop.
            Now, the word with the smaller size will
            occur before in sorted order
            */
            return Integer.compare(a.length(), b.length());
        });

        // Printing the sorted order of words
        for (String x : words)
            System.out.print(x + " ");
    }

    public static void main(String[] args) {
        String[] words = { "word", "world", "row" };
        String order = "worldabcefghijkmnpqstuvxyz";
        printSorted(words, order);
    }
}
//link - https://www.geeksforgeeks.org/sort-an-array-of-strings-based-on-the-given-order/
