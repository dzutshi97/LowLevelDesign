class Solution {
    public int subarraysDivByK(int[] nums, int k) {

        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0,1); //note!
        int sum = 0, ans=0;
        for(int i=0; i<nums.length; i++){
            sum = (sum + nums[i] ) % k;
            if(sum < 0){
                sum = sum + k;
            }
            if(hm.containsKey(sum)){
                int count = hm.get(sum);
                ans += count;
            }
            hm.put(sum, hm.getOrDefault(sum, 0)+1);

        }
        return ans;
        
    }
}
/**
Key Insight:
If two prefix sums have the same remainder modulo 
\U0001d458
k, the subarray between those prefix sums is divisible by 
\U0001d458
k.
For example:

Prefix sums: 

[4,9,9,7,4,5]
Modulo remainders: 

[4,4,4,2,4,0]
For 
k=5, when processing the array:

At 
i=1 (remainder = 4), there's 1 occurrence of remainder 

4 in the map.
At 
i=2 (remainder = 4 again), there are now 2 subarrays ending at this position divisible by \U0001d458

Simply checking sum % k == 0 would ignore these overlapping subarrays.
 */
//https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/5283729/simple-clean-code-easy-intuition-beats-92-20ms-c-java-python-kotlin/?envType=company&envId=uber&favoriteSlug=uber-three-months
