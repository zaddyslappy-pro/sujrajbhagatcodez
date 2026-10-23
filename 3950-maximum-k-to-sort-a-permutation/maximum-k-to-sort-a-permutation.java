class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        int globalAnd = -1;
        
        for (int i = 0; i < n; i++) {
            // If the element is not in its correct position and not yet visited
            if (!visited[i] && nums[i] != i) {
                int cycleAnd = -1;
                int curr = i;
                
                // Traverse the cycle
                while (!visited[curr]) {
                    visited[curr] = true;
                    if (cycleAnd == -1) {
                        cycleAnd = nums[curr];
                    } else {
                        cycleAnd &= nums[curr];
                    }
                    curr = nums[curr];
                }
                
                // Accumulate the bitwise AND across all misplaced cycles
                if (globalAnd == -1) {
                    globalAnd = cycleAnd;
                } else {
                    globalAnd &= cycleAnd;
                }
            }
        }
        
        // If the array was already sorted, globalAnd remains -1
        return globalAnd == -1 ? 0 : globalAnd;
    }
}