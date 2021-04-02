import java.util.LinkedList;

public class WallsAndGates {

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0)
            return;

        int m = rooms.length;
        int n = rooms[0].length;

        LinkedList<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(i * n + j);
                }
            }
        }

        while (!queue.isEmpty()) {
            int head = queue.poll();
            int x = head / n;
            int y = head % n;

            if (x > 0 && rooms[x - 1][y] == Integer.MAX_VALUE) {
                rooms[x - 1][y] = rooms[x][y] + 1;
                queue.add((x - 1) * n + y);
            }

            if (x < m - 1 && rooms[x + 1][y] == Integer.MAX_VALUE) {
                rooms[x + 1][y] = rooms[x][y] + 1;
                queue.add((x + 1) * n + y);
            }

            if (y > 0 && rooms[x][y - 1] == Integer.MAX_VALUE) {
                rooms[x][y - 1] = rooms[x][y] + 1;
                queue.add(x * n + y - 1);
            }

            if (y < n - 1 && rooms[x][y + 1] == Integer.MAX_VALUE) {
                rooms[x][y + 1] = rooms[x][y] + 1;
                queue.add(x * n + y + 1);
            }
        }
    }
}
