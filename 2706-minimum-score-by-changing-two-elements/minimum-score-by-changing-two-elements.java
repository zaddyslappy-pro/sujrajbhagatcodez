import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        // We can always make low score = 0 by setting two elements equal.
        // Minimize high score (max - min) using at most two changes:
        int option1 = nums[n - 3] - nums[0]; // change 2 largest elements
        int option2 = nums[n - 1] - nums[2]; // change 2 smallest elements
        int option3 = nums[n - 2] - nums[1]; // change 1 smallest and 1 largest

        return Math.min(option1, Math.min(option2, option3));
    }
}