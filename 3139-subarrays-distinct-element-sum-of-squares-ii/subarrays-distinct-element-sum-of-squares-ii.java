import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1000000007;

    long[] sum1;
    long[] sum2;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum1 = new long[4 * n];
        sum2 = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastOccurrence = new HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int prev = lastOccurrence.getOrDefault(nums[j], -1);
            
            // Update the range [prev + 1, j] by adding 1 to the distinct counts
            update(1, 0, n - 1, prev + 1, j, 1);
            
            // sum2 at the root [0, n-1] holds the sum of squares of all subarrays ending at j
            totalSum = (totalSum + sum2[1]) % MOD;
            
            lastOccurrence.put(nums[j], j);
        }

        return (int) totalSum;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // sum2_new = sum2 + 2 * val * sum1 + val^2 * len
        sum2[node] = (sum2[node] + 2 * val * sum1[node] + val * val * len) % MOD;
        // sum1_new = sum1 + val * len
        sum1[node] = (sum1[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) {
            return;
        }
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        
        sum1[node] = (sum1[2 * node] + sum1[2 * node + 1]) % MOD;
        sum2[node] = (sum2[2 * node] + sum2[2 * node + 1]) % MOD;
    }
}