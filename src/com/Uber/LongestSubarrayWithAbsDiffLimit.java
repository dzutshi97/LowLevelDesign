class Solution {
    public int longestSubarray(int[] arr, int limit) {
        //easier approach using TreeSet.
        int left =0;
        TreeSet<Integer> tset = new TreeSet<>((a,b) -> arr[a] == arr[b] ? a - b : arr[a] - arr[b]);
        int res = 1;
        tset.add(0);

        for(int right=1; right<arr.length; right++){
            tset.add(right);

            while(arr[tset.last()] - arr[tset.first()] > limit){
                // tset.remove(tset.first()); Understand. We do not have to really remove the smallest element. See e.g. below-
                 tset.remove(left++);
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
//expln - 
// https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/609705/java-simple-o-n-log-n-sliding-window-treeset/comments/534903
/**
It took me sometime to understand why are you doing set.remove(left++) instead of removing the smallest element.

Explanation:
Consider : 1 5 2 8 7 take limit = 4.
So now we can see that first three element satisfies the limit condition so they will be pushed in set
now set of indices looks like 0 2 1 i.e. values are 1 2 5, left is still 0, whereas right is 2.
Now when right become 3 i.e. pointing to 8, the limit condition fails as set 0 2 1 3 i.e. values are 1 2 5 8

now there is no need to to find where the minimum element is. Just keep removing the elements of array sequentially, irrespective of their order in set.
So first we remove 0th element i.e. 1, now left=1 set become 2 1 3 i.e. values 2 5 8
now we remove 1st element i.e. 5, now left=2 set becomes 2 3 i.e. values are 2 8; still limit condition fails
now we remove 2nd element i.e. 2, now left=3 set becomes 3 i.e values are 8,now limit condition is satisfied.

ref - https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/609705/java-simple-o-n-log-n-sliding-window-treeset/?envType=company&envId=uber&favoriteSlug=uber-three-months
 */
// ***************************************************************************************************

/**
More efficient solution using deque: https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/609743/java-detailed-explanation-sliding-window-deque-o-n/?envType=company&envId=uber&favoriteSlug=uber-three-months
Key Notes:

"Absolute difference between any two elements is less than or equal to limit" is basically => "Absolute difference between min and max elements of subarray"

Now the question becomes => find the longest subarray in which the absolute difference between min and max is less than or equal to limit. What we can do is to have two pointers: left and right, and then find the longest subarray for every right pointer (iterate it) by shrinking left pointer. And return the longest one among them
 */
