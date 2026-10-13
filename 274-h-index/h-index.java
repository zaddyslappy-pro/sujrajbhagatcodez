import java.util.Arrays;

class Solution {
    public int hIndex(int[] citations) {
        // Step 1: Array ko sort kar lete hain chote se bade kram mein
        Arrays.sort(citations);
        
        int n = citations.length;
        
        // Step 2: Array par loop chalakar check karte hain
        for (int i = 0; i < n; i++) {
            // (n - i) batata hai ki kitni papers hain jinki citations 
            // citations[i] ya usse zyada hain
            int h = n - i;
            if (citations[i] >= h) {
                return h;
            }
        }
        
        return 0;
    }
}