class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean aliceTurn = true;

        while (n >= stonesToRemove && stonesToRemove > 0) {
            n -= stonesToRemove;
            stonesToRemove--;
            aliceTurn = !aliceTurn;
        }

        // The loop stops when the current player (`aliceTurn`) cannot make a move.
        // Therefore, the other player wins.
        return !aliceTurn;
    }
}