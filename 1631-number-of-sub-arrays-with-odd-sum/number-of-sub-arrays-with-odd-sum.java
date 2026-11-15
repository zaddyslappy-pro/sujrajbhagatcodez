class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 0;
        long currentSum = 0;
        long totalSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 == 1) {
                // If current prefix sum is odd, we need previous even prefix sums
                totalSubarrays += (1 + evenCount);
                oddCount++;
            } else {
                // If current prefix sum is even, we need previous odd prefix sums
                totalSubarrays += oddCount;
                evenCount++;
            }
            
            totalSubarrays %= MOD;
        }

        return (int) totalSubarrays;
    }
}