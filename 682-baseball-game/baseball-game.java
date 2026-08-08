import java.util.Stack;

class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for (String op : operations) {
            if (op.equals("+")) {
                // Record new score as sum of previous two scores
                int top = stack.pop();
                int newScore = top + stack.peek();
                stack.push(top);       // Restore the first popped value
                stack.push(newScore);  // Push the sum
            } else if (op.equals("D")) {
                // Record new score as double the previous score
                stack.push(2 * stack.peek());
            } else if (op.equals("C")) {
                // Invalidate the previous score
                stack.pop();
            } else {
                // Parse integer string and push to stack
                stack.push(Integer.parseInt(op));
            }
        }

        // Sum up all scores in the stack
        int totalSum = 0;
        for (int score : stack) {
            totalSum += score;
        }

        return totalSum;
    }
}