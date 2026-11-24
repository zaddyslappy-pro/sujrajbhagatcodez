class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long lcm = 1;
            long gcd = 0;

            for (int j = i; j < n; j++) {
                long val = nums[j];
                gcd = (j == i) ? val : gcd(gcd, val);
                lcm = lcm(lcm, val);

                // Avoid overflow: if prod * val overflows long, break
                if (prod > Long.MAX_VALUE / val) {
                    break;
                }
                prod *= val;

                // Also avoid overflow when checking lcm * gcd
                if (lcm <= Long.MAX_VALUE / gcd && prod == lcm * gcd) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}