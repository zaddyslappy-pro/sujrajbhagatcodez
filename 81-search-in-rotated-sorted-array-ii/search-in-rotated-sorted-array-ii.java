class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Target mil gaya
            if (nums[mid] == target) {
                return true;
            }

            // Edge Case: Jab duplicates ki wajah se identify na ho sake
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }

            // Left half sorted hai
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1; // Left half me search karo
                } else {
                    low = mid + 1;  // Right half me search karo
                }
            } 
            // Right half sorted hai
            else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;  // Right half me search karo
                } else {
                    high = mid - 1; // Left half me search karo
                }
            }
        }

        return false;
    }
}