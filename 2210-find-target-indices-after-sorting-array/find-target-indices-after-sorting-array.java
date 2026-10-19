import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int count = 0;
        int lessThanTarget = 0;

        // Count elements equal to target and elements less than target
        for (int num : nums) {
            if (num < target) {
                lessThanTarget++;
            } else if (num == target) {
                count++;
            }
        }

        // Generate the sorted list of target indices
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(lessThanTarget + i);
        }

        return result;
    }
}