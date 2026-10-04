class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum possible open brackets needed
        int maxOpen = 0; // Maximum possible open brackets possible

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*' can be treated as ')', '(', or ""
                minOpen--; // Treat '*' as ')'
                maxOpen++; // Treat '*' as '('
            }

            // If maxOpen falls below 0, there are more closing brackets than open ones
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative since we can't have negative open brackets
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // Valid if it's possible to have 0 unmatched open brackets at the end
        return minOpen == 0;
    }
}