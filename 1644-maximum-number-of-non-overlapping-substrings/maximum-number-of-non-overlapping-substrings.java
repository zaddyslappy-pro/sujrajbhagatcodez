import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        
        // Step 1: Find first and last occurrences of each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        // Step 2: Find all valid minimal intervals
        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int l = first[i];
            int r = last[i];
            boolean isValid = true;

            for (int j = l; j <= r; j++) {
                int ch = s.charAt(j) - 'a';
                if (first[ch] < l) { 
                    // Contains a character whose start is outside our left bound
                    isValid = false;
                    break;
                }
                r = Math.max(r, last[ch]); // Expand right boundary
            }

            if (isValid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Step 3: Sort intervals by end index (greedy interval scheduling)
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            // Pick non-overlapping intervals
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}