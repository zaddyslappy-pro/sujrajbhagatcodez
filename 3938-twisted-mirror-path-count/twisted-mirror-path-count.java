class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[c][0] represents paths coming from the top (moving down into column c)
        // dp[c][1] represents paths coming from the left (moving right into row/column)
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    // Mirror cell: reflected directions swap/transform
                    dp[c + 1] = new int[] { dp[c + 1][1], dp[c][0] };
                } else {
                    // Empty cell: accumulate paths from both directions
                    int val = (dp[c + 1][1] + dp[c][0]) % MOD;
                    dp[c + 1] = new int[] { val, val };
                }
            }
        }
        return dp[n][0];
    }
}