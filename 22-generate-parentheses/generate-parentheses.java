import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int n) {
        // Base condition: jab valid string ban jaye
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // Open bracket tabhi add karein jab count 'n' se kam ho
        if (open < n) {
            backtrack(result, current + "(", open + 1, close, n);
        }

        // Close bracket tabhi add karein jab wo open count se kam ho
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, n);
        }
    }
}