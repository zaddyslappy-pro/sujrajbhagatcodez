import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int totalK = k1 + k2;
        
        // 1. Calculate absolute differences
        int[] diff = new int[n];
        long totalDiffSum = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiffSum += diff[i];
        }
        
        // If total operations can reduce all differences to 0
        if (totalDiffSum <= totalK) {
            return 0;
        }
        
        // 2. Count frequencies of each difference using a Map
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int d : diff) {
            if (d > 0) {
                countMap.put(d, countMap.getOrDefault(d, 0) + 1);
            }
        }
        
        // 3. Put elements into a Max-Heap (storing pairs of [difference, frequency])
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            maxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        
        // 4. Greedily reduce the largest differences first
        while (totalK > 0 && !maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int maxNum = curr[0];
            int freq = curr[1];
            
            // Determine how many elements of this value we can reduce
            int reduceCount = Math.min(totalK, freq);
            totalK -= reduceCount;
            
            // If we didn't reduce all elements of this height, push the remainder back
            if (freq > reduceCount) {
                maxHeap.offer(new int[]{maxNum, freq - reduceCount});
            }
            
            // Push the newly reduced values (maxNum - 1) back into the heap
            if (!maxHeap.isEmpty() && maxHeap.peek()[0] == maxNum - 1) {
                maxHeap.peek()[1] += reduceCount;
            } else {
                maxHeap.offer(new int[]{maxNum - 1, reduceCount});
            }
        }
        
        // 5. Calculate the final sum of squared differences
        long minSumSqDiff = 0;
        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            long num = curr[0];
            long freq = curr[1];
            minSumSqDiff += num * num * freq;
        }
        
        return minSumSqDiff;
    }
}