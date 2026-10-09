class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRights = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If we need an odd number of ')', it means a single ')' was left hanging
                if (neededRights % 2 != 0) {
                    insertions++;     // Insert a missing ')'
                    neededRights--;   // We supplied the missing ')'
                }
                neededRights += 2;    // Each '(' needs two ')'
            } else {
                neededRights--;       // Encountered ')'
                
                // Encountered ')' without a matching '('
                if (neededRights < 0) {
                    insertions++;     // Insert a '('
                    neededRights += 2; // The inserted '(' needs two ')', but we already have one ')'
                }
            }
        }

        return insertions + neededRights;
    }
}