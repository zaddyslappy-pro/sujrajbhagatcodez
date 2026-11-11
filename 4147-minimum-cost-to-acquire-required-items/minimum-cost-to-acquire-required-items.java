class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cBoth = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        long minCost = Long.MAX_VALUE;
        
        // We only need to check a few critical values for k (number of Type 3 items):
        // 1. k = 0 (buy no Type 3 items)
        // 2. k = min(n1, n2) (cover all overlapping needs with Type 3 items)
        // 3. k = max(n1, n2) (cover the larger need completely with Type 3 items)
        // Plus, if cBoth is cheaper than individual costs, we might also consider boundary checks.
        
        long minNeed = Math.min(n1, n2);
        long maxNeed = Math.max(n1, n2);
        
        long[] candidateKs = {0, minNeed, maxNeed};
        
        for (long k : candidateKs) {
            long rem1 = Math.max(0L, n1 - k);
            long rem2 = Math.max(0L, n2 - k);
            long currentCost = k * cBoth + rem1 * c1 + rem2 * c2;
            minCost = Math.min(minCost, currentCost);
        }
        
        // If cBoth is cheaper than individual costs, we should also test if extending k 
        // past minNeed or maxNeed is optimal.
        if (cBoth < c1 + c2) {
            // If cBoth is cheaper than cost1, we can extend up to n1
            long k1 = n1;
            long rem1 = Math.max(0L, n1 - k1);
            long rem2 = Math.max(0L, n2 - k1);
            minCost = Math.min(minCost, k1 * cBoth + rem1 * c1 + rem2 * c2);
            
            // If cBoth is cheaper than cost2, we can extend up to n2
            long k2 = n2;
            rem1 = Math.max(0L, n1 - k2);
            rem2 = Math.max(0L, n2 - k2);
            minCost = Math.min(minCost, k2 * cBoth + rem1 * c1 + rem2 * c2);
        }
        
        return minCost;
    }
}
