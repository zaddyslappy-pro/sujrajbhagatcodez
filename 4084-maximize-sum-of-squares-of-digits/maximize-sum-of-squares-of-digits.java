class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the sum is greater than the maximum possible sum with 'num' digits
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        // Greedily place as many 9s as possible
        while (sum >= 9 && sb.length() < num) {
            sb.append('9');
            sum -= 9;
        }

        // Place the remainder digit if we still have digits left to fill
        if (sb.length() < num) {
            sb.append(sum);
            sum = 0; // remainder has been used
        }

        // Fill any remaining trailing positions with '0'
        while (sb.length() < num) {
            sb.append('0');
        }

        return sb.toString();
    }
}