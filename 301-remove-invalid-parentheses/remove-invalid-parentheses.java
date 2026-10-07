import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);

        boolean foundValidAtCurrentLevel = false;

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (isValid(current)) {
                result.add(current);
                foundValidAtCurrentLevel = true;
            }

            // Once a valid string is found at a level, we stop expanding further levels
            if (foundValidAtCurrentLevel) continue;

            // Generate next level strings by removing 1 parenthesis at a time
            for (int i = 0; i < current.length(); i++) {
                char ch = current.charAt(i);
                
                // Skip non-parenthesis characters
                if (ch != '(' && ch != ')') continue;

                String next = current.substring(0, i) + current.substring(i + 1);

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return result;
    }

    // Helper method to check if a parenthesis string is valid
    private boolean isValid(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                count--;
                if (count < 0) return false; // More closing than opening
            }
        }
        return count == 0;
    }
}