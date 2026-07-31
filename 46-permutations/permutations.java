import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> currentPath, boolean[] used, List<List<Integer>> result) {
        // Base Case: If current combination size equals input length, store a copy
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if the element is already included in currentPath
            if (used[i]) {
                continue;
            }

            // 1. CHOOSE
            currentPath.add(nums[i]);
            used[i] = true;

            // 2. RECURSE
            backtrack(nums, currentPath, used, result);

            // 3. UNCHOOSE (Backtrack)
            currentPath.remove(currentPath.size() - 1);
            used[i] = false;
        }
    }
}