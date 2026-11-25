import java.util.*;

class Solution {
    private static final long BASE1 = 131L;
    private static final long BASE2 = 137L;
    private static final long MOD1  = 1_000_000_007L;
    private static final long MOD2  = 1_000_000_009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueOfLength(nums, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean hasUniqueOfLength(int[] nums, int k) {
        int n = nums.length;
        long pow1 = 1, pow2 = 1;
        for (int i = 0; i < k - 1; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }

        long h1 = 0, h2 = 0;
        // value >= 0: unique so far at that starting index
        // value == -1: seen more than once (duplicate)
        Map<Long, Integer> seen = new HashMap<>(Math.max(16, (n - k + 1) * 2));

        for (int i = 0; i < n; i++) {
            long val = ((long) nums[i] % MOD1 + MOD1) % MOD1 + 1;
            long val2 = ((long) nums[i] % MOD2 + MOD2) % MOD2 + 1;

            if (i >= k) {
                long out1 = ((long) nums[i - k] % MOD1 + MOD1) % MOD1 + 1;
                long out2 = ((long) nums[i - k] % MOD2 + MOD2) % MOD2 + 1;
                h1 = (h1 - (out1 * pow1) % MOD1 + MOD1) % MOD1;
                h2 = (h2 - (out2 * pow2) % MOD2 + MOD2) % MOD2;
            }

            h1 = (h1 * BASE1 + val) % MOD1;
            h2 = (h2 * BASE2 + val2) % MOD2;

            if (i >= k - 1) {
                int start = i - k + 1;
                long key = (h1 << 32) | (h2 & 0xffffffffL);
                Integer prev = seen.putIfAbsent(key, start);
                if (prev != null && prev != -1) {
                    seen.put(key, -1);
                }
            }
        }

        for (int startIdx : seen.values()) {
            if (startIdx >= 0) {
                return true;
            }
        }
        return false;
    }
}