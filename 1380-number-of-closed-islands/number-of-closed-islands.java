class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Step 1: Flood fill all 0s connected to the boundary and turn them into 1s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Check if the cell is on the boundary and is land (0)
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && grid[i][j] == 0) {
                    dfs(grid, i, j, m, n);
                }
            }
        }
        
        // Step 2: Count the remaining closed islands
        int closedIslandsCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    closedIslandsCount++;
                    dfs(grid, i, j, m, n); // Sink the entire closed island
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c, int m, int n) {
        // Base case: Check bounds and if it's water (1)
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 1) {
            return;
        }
        
        // Mark current cell as visited by turning it into water (1)
        grid[r][c] = 1;
        
        // Traverse all 4 directions
        dfs(grid, r + 1, c, m, n);
        dfs(grid, r - 1, c, m, n);
        dfs(grid, r, c + 1, m, n);
        dfs(grid, r, c - 1, m, n);
    }
}