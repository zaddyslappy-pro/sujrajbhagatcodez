class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        // freeGain[i] = number of items j != i where items[j][0] is divisible by items[i][0]
        int[] freeGain = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    count++;
                }
            }
            freeGain[i] = count;
        }

        // dp[w] = max (1 + freeGain[i]) sum using trigger cost <= w
        long[] dp = new long[budget + 1];
        for (int i = 0; i < n; i++) {
            int cost = items[i][1];
            long gain = 1L + freeGain[i];
            if (cost > budget) continue;
            for (int w = budget; w >= cost; w--) {
                if (dp[w - cost] + gain > dp[w]) {
                    dp[w] = dp[w - cost] + gain;
                }
            }
        }

        long ans = 0;
        for (int w = 0; w <= budget; w++) {
            long total = dp[w] + (budget - w) / minPrice;
            if (total > ans) {
                ans = total;
            }
        }

        return (int) ans;
    }
}