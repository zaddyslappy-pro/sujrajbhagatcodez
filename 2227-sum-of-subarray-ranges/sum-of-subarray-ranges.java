import java.util.Stack;

class Solution {
    public long subArrayRanges(int[] nums) {
        // Range sum = Total Max Contribution - Total Min Contribution
        return getMaxSum(nums) - getMinSum(nums);
    }

    // Har subarray ke Maximum elements ka total sum nikalne ke liye
    private long getMaxSum(int[] nums) {
        int n = nums.length;
        long totalMax = 0;
        Stack<Integer> stack = new Stack<>();

        // Loop i <= n tak chalega taaki end me stack clear ho sake
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] < nums[i])) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;

                // Subarrays ki count jisme nums[mid] Maximum hai
                long count = (long) (mid - left) * (right - mid);
                totalMax += count * nums[mid];
            }
            stack.push(i);
        }
        return totalMax;
    }

    // Har subarray ke Minimum elements ka total sum nikalne ke liye
    private long getMinSum(int[] nums) {
        int n = nums.length;
        long totalMin = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] > nums[i])) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;

                // Subarrays ki count jisme nums[mid] Minimum hai
                long count = (long) (mid - left) * (right - mid);
                totalMin += count * nums[mid];
            }
            stack.push(i);
        }
        return totalMin;
    }
}