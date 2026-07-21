class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m*n;

        k = k % total; //to remove unnecessary cycles
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<m; i++){
            List<Integer> row = new ArrayList<>();
            for(int j=0; j<n; j++){
                row.add(0);
            }
            result.add(row);
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int OldIdx = i*n + j;
                int newIdx = (OldIdx + k) % total; //to make sure that elementsts are in the boundary 
                int newRow = newIdx / n;
                int newCol = newIdx % n;
                result.get(newRow).set(newCol, grid[i][j]); 
            }
        }
        return result;
    }
}