class Solution {
    private int minVal;

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        minVal = Integer.MAX_VALUE;

        boolean[][][] dp = new boolean[m][n][1024];

        rec(grid, 0, 0, 0, dp);

        return minVal;
    }

    private void rec(
        int[][] grid,
        int i,
        int j,
        int curr,
        boolean[][][] dp
    ) {
        int m = grid.length;
        int n = grid[0].length;

        if (i >= m || j >= n) {
            return;
        }
        if (i == m - 1 && j == n - 1) {
            minVal = Math.min(
                minVal,
                curr ^ grid[i][j]
            );
            return;
        }
        if (dp[i][j][curr]) {
            return;
        }
        dp[i][j][curr] = true;

        int nextXor = curr ^ grid[i][j];
        rec(grid, i, j + 1, nextXor, dp);
        rec(grid, i + 1, j, nextXor, dp);
    }
}