import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int toPlusOne = dp.getOrDefault(x, 0) + 1;
            int toSame = dp.getOrDefault(x - 1, 0) + 1;

            dp.put(x + 1, toPlusOne);
            dp.put(x, toSame);

            ans = Math.max(ans, Math.max(toPlusOne, toSame));
        }

        return ans;
    }
}