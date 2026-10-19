class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Step 1: Calculate prefix (left) products
        // answer[i] will store the product of all elements to the left of nums[i]
        answer[0] = 1; // No elements to the left of index 0
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Step 2: Calculate suffix (right) products and combine on the fly
        int rightProduct = 1; // Tracks product of all elements to the right of nums[i]
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct *= nums[i]; // Update right product for the next iteration
        }

        return answer;
    }
}