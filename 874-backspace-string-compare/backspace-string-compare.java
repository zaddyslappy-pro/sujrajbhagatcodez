class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            
            // 1. Find index of next valid character in string s
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break; // Found a valid character
                }
            }

            // 2. Find index of next valid character in string t
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break; // Found a valid character
                }
            }

            // 3. Compare the current valid characters
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } 
            // If one string has characters left and the other doesn't
            else if ((i >= 0) != (j >= 0)) {
                return false;
            }

            // Move to the next characters
            i--;
            j--;
        }

        return true;
    }
}