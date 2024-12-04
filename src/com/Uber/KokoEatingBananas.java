class Solution {

    public int findMax(int[] v) {
        int maxi = Integer.MIN_VALUE;
        int n = v.length;

        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, v[i]);
        }
        return maxi;
    }

    private int calculateTotalHours(int[] v, int hourly) {
        int totalH = 0;
        int n = v.length;

        for (int i = 0; i < n; i++) {
            totalH += Math.ceil((double)v[i]/(double)hourly);//remmeber to add double and ceil
        }
        return totalH;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = findMax(piles);
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low)/2;
            int totalH = calculateTotalHours(piles, mid);
            if (totalH <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
//editorial: Brute force visualization
// understanding question: https://leetcode.com/problems/koko-eating-bananas/description/comments/2119911
