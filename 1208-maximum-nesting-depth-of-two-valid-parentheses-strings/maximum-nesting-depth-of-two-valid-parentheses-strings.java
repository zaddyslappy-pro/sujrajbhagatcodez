class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                depth++;
                answer[i] = depth % 2; // Assign based on current depth
            } else {
                answer[i] = depth % 2; // Assign matching closing bracket to same group
                depth--;
            }
        }

        return answer;
    }
}