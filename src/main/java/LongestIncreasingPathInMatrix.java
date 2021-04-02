public class LongestIncreasingPathInMatrix {
/*    Given an m x n integers matrix, return the length of the longest increasing path in matrix.

    From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



    Example 1:


    Input: matrix = [[9,9,4],
                     [6,6,8],
                     [2,1,1]]
    Output: 4
    Explanation: The longest increasing path is [1, 2, 6, 9].*/

    public static void main(String[] args) {
        int matrix[][] = {{9,9,4},
                {6,6,8},
                {2,1,1}};

        int mat[][] = {
                { 1, 2, 3, 4 },
                { 2, 2, 3, 4 },
                { 3, 2, 3, 4 },
                { 4, 5, 6, 7 }};

        LongestIncreasingPathInMatrix l = new LongestIncreasingPathInMatrix();
        System.out.println(l.longestIncreasingPath(mat));
    }

    boolean[][] visited;
    int[][] dp;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        visited = new boolean[m][n];
        dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int x, int y) {
        if (visited[x][y]) {
            return dp[x][y];
        }
        // Initialize:
        dp[x][y] = 1;

        // Calculating, dfs
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (validateCoordinate(matrix, x, y, nx, ny)) {
                dp[x][y] = Math.max(dp[x][y], dfs(matrix, nx, ny) + 1);
            }
        }
        visited[x][y] = true;
        return dp[x][y];
    }

    private boolean validateCoordinate(int[][] matrix, int x, int y, int nx, int ny) {
        return nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length
                && matrix[x][y] < matrix[nx][ny];
    }
}
