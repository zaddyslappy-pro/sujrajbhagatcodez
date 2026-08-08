import java.util.Stack;

class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> q = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Check if current char matches the top of the stack BEFORE pushing
            if (!q.isEmpty() && q.peek() == ch) {
                q.pop(); // Remove the adjacent duplicate
            } else {
                q.push(ch); // Add character if it's not a duplicate
            }
        }

        // Build result string from the remaining characters in the stack
        StringBuilder sb = new StringBuilder();
        for (char c : q) {
            sb.append(c);
        }

        return sb.toString();
    }
}