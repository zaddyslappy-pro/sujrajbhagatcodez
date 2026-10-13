
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        // Step 1: Square every element
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        // Step 2: Sort squares in ascending order
        Arrays.sort(squares);

        // Step 3: Subtract the smallest half
        // and add the remaining larger half
        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                ans -= squares[i];
            } else {
                ans += squares[i];
            }
        }

        return ans;
    }
}
