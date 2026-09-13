import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // 1. Collect coordinates of all 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) list1.add(new int[]{r, c});
                if (img2[r][c] == 1) list2.add(new int[]{r, c});
            }
        }

        // Map to store frequency of each translation vector (dr, dc)
        Map<String, Integer> count = new HashMap<>();
        int maxOverlap = 0;

        // 2. Compute vector differences between every pair of 1s
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];
                
                String key = dr + "," + dc;
                int currentCount = count.getOrDefault(key, 0) + 1;
                count.put(key, currentCount);
                
                maxOverlap = Math.max(maxOverlap, currentCount);
            }
        }

        return maxOverlap;
    }
}