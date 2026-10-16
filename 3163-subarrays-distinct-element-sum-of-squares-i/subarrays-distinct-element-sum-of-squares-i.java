import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Generate all possible subarrays
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctSet = new HashSet<>();
            for (int j = i; j < n; j++) {
                // Add the current element to the set
                distinctSet.add(nums.get(j));
                
                // Get the distinct count for nums[i..j]
                int distinctCount = distinctSet.size();
                
                // Add the square of the distinct count to the total sum
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}