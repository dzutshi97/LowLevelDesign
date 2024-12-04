import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    private int helper(int day, int[] costs, Set<Integer> travelDays, int[] dp) {
        // Base case: if day exceeds 365, no cost is needed beyond the last travel day.
        if (day > 365) {
            return 0;
        }

        // If already calculated, return the memoized result.
        if (dp[day] != -1) {
            return dp[day];
        }

        // If the current day is not a travel day, skip to the next day.
        if (!travelDays.contains(day)) {
            return dp[day] = helper(day + 1, costs, travelDays, dp);
        }

        // Calculate costs for 1-day, 7-day, and 30-day tickets.
        int day1 = costs[0] + helper(day + 1, costs, travelDays, dp);
        int day7 = costs[1] + helper(day + 7, costs, travelDays, dp);
        int day30 = costs[2] + helper(day + 30, costs, travelDays, dp);

        // Store the minimum cost in dp and return it.
        return dp[day] = Math.min(day1, Math.min(day7, day30));
    }

    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> travelDays = new HashSet<>();
        for (int day : days) {
            travelDays.add(day);
        }

        // Memoization array to store the minimum cost from a given day.
        int[] dp = new int[366];
        for (int i = 0; i < 366; i++) {
            dp[i] = -1;  // Initialize the memoization array.
        }

        // Start the helper function from the first day of travel.
        return helper(1, costs, travelDays, dp);
    }
}
//https://leetcode.com/problems/minimum-cost-for-tickets/solutions/2345641/dp-solution-as-striver-taught-beginner-friendly/
