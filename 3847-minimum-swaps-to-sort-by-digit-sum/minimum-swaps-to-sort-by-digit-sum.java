import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Step 1: Pair each element with its index, value, and digit sum
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(nums[i], getDigitSum(nums[i]), i);
        }

        // Step 2: Sort based on (digitSum ASC, value ASC)
        Arrays.sort(elements, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }
            return Integer.compare(a.val, b.val);
        });

        // Step 3: Count permutation cycles
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // Skip if already visited or already in its correct position
            if (visited[i] || elements[i].originalIndex == i) {
                continue;
            }

            // Compute cycle size
            int cycleSize = 0;
            int curr = i;

            while (!visited[curr]) {
                visited[curr] = true;
                curr = elements[curr].originalIndex; // Follow pointer to original index
                cycleSize++;
            }

            if (cycleSize > 0) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    // Helper method to compute the sum of digits of a number
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // Custom helper class to store details about each array item
    private static class Element {
        int val;
        int digitSum;
        int originalIndex;

        Element(int val, int digitSum, int originalIndex) {
            this.val = val;
            this.digitSum = digitSum;
            this.originalIndex = originalIndex;
        }
    }
}