class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;

                // Add '(' only if it is NOT the outermost one
                if (balance > 1) {
                    ans.append(ch);
                }

            } else {
                balance--;

                // Add ')' only if it is NOT the outermost one
                if (balance > 0) {
                    ans.append(ch);
                }
            }
        }

        return ans.toString();
    }
}