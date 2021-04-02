import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class MinimumAreaRectangle {

/*  Given a set of points in the x and y axes, determine the minimum area of a rectangle formed from these points,
    with sides parallel to the x and y axes.
    Analysis

In the first impression, this problem may be solved by 3 different approaches:


First, the max value on the grid is a constant, 40000. Maybe we can iterate over all possibilities of the grip. By calculating the total number of operations, this is not good. Its time is at list O(N^4).
Second, maybe we can use a DFS method to search each point and see if a rectangle can be formed from each point. This may work, but this may be a simpler solution.
Third, by looking at the grid, we can think about what is the requirement of a rectangle. We can see that if there is a rectangle, there should be 2 points for the diagonal (x1, y1) and (x2, y2). There should also be two other points corresponding to the two diagonal points: (x1, y2) and (x2, y1). So the solution naturally come out. We iterate over all possibilities of two diagonal points and see if the other two points exist.

So we go with the third solution. We can use a hash map to make point searching constant time. The time complexity of the solution below is O(N^2).*/

    public int minAreaRect(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            }
        });

        HashMap<Integer, HashSet<Integer>> xMap = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> yMap = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];

            //x map
            HashSet<Integer> setX = xMap.get(x);

            if (setX == null) {
                setX = new HashSet<Integer>();
                xMap.put(x, setX);
            }

            setX.add(y);

            //y map
            HashSet<Integer> setY = yMap.get(y);

            if (setY == null) {
                setY = new HashSet<Integer>();
                yMap.put(y, setY);
            }

            setY.add(x);
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                if (xMap.get(x1).contains(y2) && yMap.get(y1).contains(x2)) {
                    int area = Math.abs((x1 - x2) * (y1 - y2));
                    if (area > 0) {
                        result = Math.min(result, area);
                    }
                }
            }
        }

        if (result == Integer.MAX_VALUE) {
            return 0;
        }

        return result;
    }
}
