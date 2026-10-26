class Solution {
    public int countIslands(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If we find an unvisited land cell (value > 0)
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j);
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Boundary check and check if the cell is water or already visited (0)
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 0) {
            return 0;
        }

        long currentVal = grid[r][c];
        // Mark the cell as visited by setting it to 0
        grid[r][c] = 0;

        // Explore all 4-directional neighbors
        long sum = currentVal
                 + dfs(grid, r + 1, c)
                 + dfs(grid, r - 1, c)
                 + dfs(grid, r, c + 1)
                 + dfs(grid, r, c - 1);

        return sum;
    }
}