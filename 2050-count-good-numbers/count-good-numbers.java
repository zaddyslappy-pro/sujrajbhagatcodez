class Solution {

    static final int MOD = 1000000007;

    public int countGoodNumbers(long n) {

        long even = (n + 1) / 2;
        long odd = n / 2;

        long ans = (power(5, even) * power(4, odd)) % MOD;

        return (int) ans;
    }

    private long power(long base, long exp) {

        long result = 1;

        while (exp > 0) {

            // If exp is odd
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exp = exp / 2;
        }

        return result;
    }
}