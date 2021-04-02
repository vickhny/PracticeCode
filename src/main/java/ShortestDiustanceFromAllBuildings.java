import java.util.LinkedList;

/*This problem can be solved by BFS. We define one matrix for tracking the distance from each building,
        and another matrix for tracking the number of buildings which can be reached.*/
public class ShortestDiustanceFromAllBuildings {

    public static void main(String[] args) {

    }

    public int shortestDistance(int[][] grid) {
        int[][] distance = new int[grid.length][grid[0].length];
        int[][] reach = new int[grid.length][grid[0].length];


        int numBuilding = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    helper(grid, distance, reach, i, j);
                    numBuilding++;
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && reach[i][j] == numBuilding) {
                    result = Math.min(result, distance[i][j]);
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void helper(int[][] grid, int[][] distance, int[][] reach, int i, int j) {

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        //two queue, one for direction, one for distance tracking
        LinkedList<int[]> q = new LinkedList<>();
        LinkedList<Integer> qDist = new LinkedList<>();

        q.offer(new int[]{i, j});
        qDist.offer(1);

        while (!q.isEmpty()) {
            int[] head = q.poll();
            int dis = qDist.poll();

            for (int k = 0; k < 4; k++) {
                int x = head[0] + dx[k];
                int y = head[1] + dy[k];

                if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 0) {
                    grid[x][y] = -1;

                    q.offer(new int[]{x, y});
                    qDist.offer(dis + 1);

                    distance[x][y] += dis;
                    reach[x][y]++;
                }
            }
        }

        for (int m = 0; m < grid.length; m++) {
            for (int n = 0; n < grid[0].length; n++) {
                if (grid[m][n] == -1) {
                    grid[m][n] = 0;
                }
            }
        }
    }
}
