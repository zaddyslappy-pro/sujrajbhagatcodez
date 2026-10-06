class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int moves = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--;
                } else {
                    moves++; // Unmatched closing parenthesis
                }
            }
        }

        return moves + openCount; // Total additions needed
    }
}