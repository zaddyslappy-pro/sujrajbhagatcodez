class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                // Keep the top row (i == 0) and the last column (j == n - 1) as free cells ('.')
                // Fill all other cells with obstacles ('#')
                if (i == 0 || j == n - 1) {
                    sb.append('.');
                } else {
                    sb.append('#');
                }
            }
            grid[i] = sb.toString();
        }
        
        return grid;
    }
}