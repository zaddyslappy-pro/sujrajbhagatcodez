class Solution {

    public void solveSudoku(char[][] board) {
        sudokuSolver(board, 0, 0);
    }

    public boolean sudokuSolver(char[][] board, int row, int col) {

        // Base case
        if (row == 9) {
            return true;
        }

        int nextRow = row;
        int nextCol = col + 1;

        if (col + 1 == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }

        // Skip filled cells
        if (board[row][col] != '.') {
            return sudokuSolver(board, nextRow, nextCol);
        }

        // Try digits '1' to '9'
        for (char digit = '1'; digit <= '9'; digit++) {

            if (isSafe(board, row, col, digit)) {

                // Place digit
                board[row][col] = digit;

                // Solve remaining board
                if (sudokuSolver(board, nextRow, nextCol)) {
                    return true;
                }

                // Backtrack
                board[row][col] = '.';
            }
        }

        return false;
    }

    public boolean isSafe(char[][] board, int row, int col, char digit) {

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == digit) {
                return false;
            }
        }

        // Check row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == digit) {
                return false;
            }
        }

        // Check 3x3 box
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }
}