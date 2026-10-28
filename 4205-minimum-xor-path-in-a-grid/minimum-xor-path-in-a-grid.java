import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[j] will store the set of all possible XOR path values at column j for the current row
        Set<Integer>[] dp = new HashSet[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new HashSet<>();
        }
        
        // Base case: top-left cell (0, 0)
        dp[0].add(grid[0][0]);
        
        // Initialize first row (can only come from the left)
        for (int j = 1; j < n; j++) {
            for (int prevXor : dp[j - 1]) {
                dp[j].add(prevXor ^ grid[0][j]);
            }
        }
        
        // Fill the DP table for the rest of the grid
        for (int i = 1; i < m; i++) {
            // Initialize the first column of the current row (can only come from above)
            Set<Integer> nextRowFirstCol = new HashSet<>();
            for (int prevXor : dp[0]) {
                nextRowFirstCol.add(prevXor ^ grid[i][0]);
            }
            dp[0] = nextRowFirstCol;
            
            // Fill remaining columns in the current row
            for (int j = 1; j < n; j++) {
                Set<Integer> currentXors = new HashSet<>();
                // From top
                for (int prevXor : dp[j]) {
                    currentXors.add(prevXor ^ grid[i][j]);
                }
                // From left
                for (int prevXor : dp[j - 1]) {
                    currentXors.add(prevXor ^ grid[i][j]);
                }
                dp[j] = currentXors;
            }
        }
        
        // Find the minimum XOR value in the destination cell's set
        int minResult = Integer.MAX_VALUE;
        for (int val : dp[n - 1]) {
            minResult = Math.min(minResult, val);
        }
        
        return minResult;
    }
}