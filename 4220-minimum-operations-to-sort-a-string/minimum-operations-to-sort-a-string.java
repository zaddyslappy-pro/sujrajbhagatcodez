class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        
        // Check if already sorted
        boolean isSorted = true;
        for (int i = 1; i < n; ++i) {
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }
        
        // Length 2 cannot be sorted if it's out of order
        if (n == 2) {
            return -1;
        }
        
        char mn = cs[0], mx = cs[0];
        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
        }
        
        // 1 operation is only valid if min is at the start AND doesn't appear elsewhere incorrectly, 
        // or max is at the end. Specifically, LeetCode's exact check for 1 operation:
        if (cs[0] == mn || cs[n - 1] == mx) {
            // Wait, let's fix the condition: 
            // 1 operation works if s[0] == mn (and we sort the rest) OR s[n-1] == mx (and we sort the prefix)
            // But if elements in between conflict, we need to be precise.
        }
        
        // Standard concise accepted approach:
        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }
        
        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }
        
        return 3;
    }
}