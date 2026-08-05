import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long MOD = 1_000_000_007;
        
        // Monotonic increasing stack storing indices
        Deque<Integer> stack = new ArrayDeque<>();
        long totalSum = 0;
        
        // Iterate up to n (i == n acts as a virtual boundary with value 0)
        for (int i = 0; i <= n; i++) {
            // Virtual element 0 at index n forces stack clearing at the end
            int currentVal = (i == n) ? 0 : arr[i];
            
            // Pop elements that are strictly greater than or equal to currentVal
            while (!stack.isEmpty() && arr[stack.peek()] >= currentVal) {
                int midIndex = stack.pop();
                
                // Right boundary distance: distance from midIndex to current index i
                int rightCount = i - midIndex;
                
                // Left boundary distance: distance from midIndex to previous smaller element index
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int leftCount = midIndex - leftIndex;
                
                // Total subarrays where arr[midIndex] is minimum
                long count = (long) leftCount * rightCount;
                
                // Add contribution to total sum
                totalSum = (totalSum + arr[midIndex] * count) % MOD;
            }
            
            stack.push(i);
        }
        
        return (int) totalSum;
    }
}