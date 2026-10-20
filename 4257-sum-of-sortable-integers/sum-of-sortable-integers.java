import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        // Iterate over all possible divisors k of n
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (isSortable(nums, n, k)) {
                    ans += k;
                }
            }
        }

        return ans;
    }

    private boolean isSortable(int[] nums, int n, int k) {
        int prevBlockMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i += k) {
            int blockMin = Integer.MAX_VALUE;
            int blockMax = Integer.MIN_VALUE;
            int drops = 0;

            for (int j = i; j < i + k; j++) {
                blockMin = Math.min(blockMin, nums[j]);
                blockMax = Math.max(blockMax, nums[j]);

                // Count internal drops
                if (j + 1 < i + k && nums[j] > nums[j + 1]) {
                    drops++;
                }
            }

            // Check if this block can be rotated into non-decreasing order
            if (drops > 1) {
                return false;
            }
            if (drops == 1 && nums[i + k - 1] > nums[i]) {
                return false;
            }

            // Check if this block's elements are all >= previous block's elements
            if (blockMin < prevBlockMax) {
                return false;
            }

            prevBlockMax = blockMax;
        }

        return true;
    }
}