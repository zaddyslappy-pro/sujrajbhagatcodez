class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        int startRev = -1, startShift = -1;
        
        // 1. Check if nums matches any of the n forward cyclic shift states
        for (int s = 0; s < n; s++) {
            boolean match = true;
            for (int i = 0; i < n; i++) {
                if (nums[i] != (s + i) % n) {
                    match = false;
                    break;
                }
            }
            if (match) {
                startRev = 0;
                startShift = s;
                break;
            }
        }
        
        // 2. Check if nums matches any of the n reversed states
        if (startRev == -1) {
            for (int s = 0; s < n; s++) {
                boolean match = true;
                for (int i = 0; i < n; i++) {
                    if (nums[i] != (s + n - 1 - i) % n) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    startRev = 1;
                    startShift = s;
                    break;
                }
            }
        }
        
        // If not found in the 2n valid states, sorting is impossible
        if (startRev == -1) {
            return -1;
        }
        
        int startNode = startRev * n + startShift;
        int targetNode = 0; // Sorted array is rev = 0, shift = 0
        
        if (startNode == targetNode) {
            return 0;
        }
        
        // 3. BFS on the 2n state graph
        int totalStates = 2 * n;
        int[] dist = new int[totalStates];
        java.util.Arrays.fill(dist, -1);
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.offer(startNode);
        dist[startNode] = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int rev = curr / n;
            int shift = curr % n;
            
            if (curr == targetNode) {
                return dist[curr];
            }
            
            // Transition 1: Rotate Left (L)
            int nextRev1 = rev;
            int nextShift1 = (rev == 0) ? (shift + 1) % n : (shift - 1 + n) % n;
            int nextNode1 = nextRev1 * n + nextShift1;
            
            if (dist[nextNode1] == -1) {
                dist[nextNode1] = dist[curr] + 1;
                queue.offer(nextNode1);
            }
            
            // Transition 2: Reverse (Rev)
            int nextRev2 = 1 - rev;
            int nextShift2 = shift;
            int nextNode2 = nextRev2 * n + nextShift2;
            
            if (dist[nextNode2] == -1) {
                dist[nextNode2] = dist[curr] + 1;
                queue.offer(nextNode2);
            }
        }
        
        return dist[targetNode];
    }
}