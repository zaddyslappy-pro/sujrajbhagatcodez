class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] represents the minimum cost to reach step i
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0; // Starting point
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            
            // Try all possible jumps: i + 1, i + 2, i + 3
            for (int j = i + 1; j <= Math.min(n, i + 3); j++) {
                // costs[j - 1] because costs array is 1-indexed (costs[1] is at index 0)
                int jumpCost = costs[j - 1] + (j - i) * (j - i);
                dp[j] = Math.min(dp[j], dp[i] + jumpCost);
            }
        }
        
        return dp[n];
    }
}