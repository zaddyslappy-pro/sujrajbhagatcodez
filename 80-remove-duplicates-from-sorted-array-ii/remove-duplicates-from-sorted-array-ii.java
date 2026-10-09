class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int k = 2; // Pointer to place the next valid element

        for (int i = 2; i < nums.length; i++) {
            // Check if the current element is different from the element 2 positions back
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}