import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        table = new long[n + 1][n + 1];

        // Step 1: Precompute Pascal's triangle for combinations C(n, k)
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 to exclude the original array ordering
        return (int) (dfs(list) - 1 + MOD) % MOD;
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            int val = nums.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int L = left.size();
        int R = right.size();

        // Ways to interleave left and right subtrees: C(L + R, L)
        long ways = table[L + R][L];
        
        // Multiply by combinations of left and right subtrees modulo MOD
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}