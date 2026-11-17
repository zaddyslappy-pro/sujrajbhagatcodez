class Solution {
    int[][] memoCost;
    Integer[][] memoDp;
    String str;
    int n;

    public int minimumChanges(String s, int k) {
        this.str = s;
        this.n = s.length();
        this.memoCost = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memoCost[i], -1);
        }
        this.memoDp = new Integer[n][k + 1];

        return solveDp(0, k);
    }

    private int getCost(int i, int j) {
        if (i > j) return 0;
        if (memoCost[i][j] != -1) return memoCost[i][j];

        int len = j - i + 1;
        int minChanges = 1000000; // Use a large value if no proper divisor exists (e.g. length 1)

        // Check all proper divisors d of length
        for (int d = 1; d < len; d++) {
            if (len % d == 0) {
                int currentCost = 0;
                // Check each of the d groups
                for (int offset = 0; offset < d; offset++) {
                    int left = i + offset;
                    int right = i + len - d + offset;
                    while (left < right) {
                        if (str.charAt(left) != str.charAt(right)) {
                            currentCost++;
                        }
                        left += d;
                        right -= d;
                    }
                }
                minChanges = Math.min(minChanges, currentCost);
            }
        }
        return memoCost[i][j] = minChanges;
    }

    private int solveDp(int idx, int rem) {
        if (rem == 1) {
            return getCost(idx, n - 1);
        }
        if (memoDp[idx][rem] != null) {
            return memoDp[idx][rem];
        }

        int res = Integer.MAX_VALUE / 2;
        for (int j = idx; j <= n - rem; j++) {
            int cost = getCost(idx, j) + solveDp(j + 1, rem - 1);
            res = Math.min(res, cost);
        }

        return memoDp[idx][rem] = res;
    }
}