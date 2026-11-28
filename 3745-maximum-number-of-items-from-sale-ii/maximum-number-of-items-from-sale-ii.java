
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int maxFactor = n;

        // Step 1: Count frequency of each factor
        int[] freq = new int[maxFactor + 1];

        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Count how many items each factor can unlock
        int[] gain = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j += i) {
                gain[i] += freq[j];
            }

            // Exclude the item itself
            gain[i]--;
        }

        // Step 3: Sort items by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long ans = 0;

        // Step 4: Buy valuable items first
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // A bonus item must cost less than 2 * minPrice
            if (price >= 2 * minPrice) {
                break;
            }

            int available = gain[factor];

            if (available <= 0) {
                continue;
            }

            long buy = Math.min(
                available,
                remaining / price
            );

            ans += buy * 2;
            remaining -= buy * price;
        }

        // Step 5: Spend remaining budget on cheapest items
        ans += remaining / minPrice;

        return (int) ans;
    }
}
