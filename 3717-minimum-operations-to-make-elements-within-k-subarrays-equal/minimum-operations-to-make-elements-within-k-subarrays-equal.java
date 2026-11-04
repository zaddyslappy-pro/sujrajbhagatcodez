
import java.util.*;

class Solution {

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window of size x
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < n; i++) {

            // Add current element
            window.add(nums[i]);

            // Remove element outside the sliding window
            if (i >= x) {
                window.remove(nums[i - x]);
            }

            // Window of size x is ready
            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        // Step 2: Dynamic Programming

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        // Zero subarrays require zero operations
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // Step 3: Choose k non-overlapping windows
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Option 1: Skip current element
                dp[i][j] = dp[i - 1][j];

                // Option 2: Select window ending at i-1
                if (i >= x && dp[i - x][j - 1] != INF) {

                    long take = dp[i - x][j - 1]
                              + cost[i - x];

                    dp[i][j] = Math.min(dp[i][j], take);
                }
            }
        }

        return dp[n][k];
    }

    // Maintains two halves of the sliding window
    static class MedianWindow {

        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();

        int lowerSize = 0;
        int upperSize = 0;

        long lowerSum = 0;
        long upperSum = 0;

        void add(int val) {

            if (lower.isEmpty() || val <= lower.lastKey()) {

                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSize++;
                lowerSum += val;

            } else {

                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSize++;
                upperSum += val;
            }

            balance();
        }

        void remove(int val) {

            if (lower.containsKey(val)) {

                removeValue(lower, val);
                lowerSize--;
                lowerSum -= val;

            } else {

                removeValue(upper, val);
                upperSize--;
                upperSum -= val;
            }

            balance();
        }

        void removeValue(TreeMap<Integer, Integer> map, int val) {

            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        void balance() {

            // Lower half should have equal or one extra element
            while (lowerSize > upperSize + 1) {

                int val = lower.lastKey();

                removeValue(lower, val);
                lowerSize--;
                lowerSum -= val;

                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSize++;
                upperSum += val;
            }

            while (lowerSize < upperSize) {

                int val = upper.firstKey();

                removeValue(upper, val);
                upperSize--;
                upperSum -= val;

                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSize++;
                lowerSum += val;
            }
        }

        long getCost() {

            // Median is the largest element in lower half
            long median = lower.lastKey();

            // Cost of converting lower half to median
            long leftCost = median * lowerSize - lowerSum;

            // Cost of converting upper half to median
            long rightCost = upperSum - median * upperSize;

            return leftCost + rightCost;
        }
    }
}
