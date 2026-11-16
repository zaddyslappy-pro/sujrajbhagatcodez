class Solution {
    public String digitSum(String s, int k) {
        // Repeat rounds until the string length is at most k
        while (s.length() > k) {
            StringBuilder sb = new StringBuilder();
            
            // Process the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                
                // Sum digits in the current group, stopping safely at s.length()
                for (int j = i; j < i + k && j < s.length(); j++) {
                    sum += s.charAt(j) - '0';
                }
                
                // Append the group's sum to form the next round's string
                sb.append(sum);
            }
            
            // Update s for the next round
            s = sb.toString();
        }
        
        return s;
    }
}