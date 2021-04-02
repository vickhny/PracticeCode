import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JackTravelCities {

    public static void main(String[] args) {
        int a[] = {0, 9, 0, 2, 6, 8, 0, 8, 3, 0};
        Graph g = new Graph(a.length);

        for (int i = 0; i < a.length; i++) {
            if (a[i] != i) {
                g.addEdge(a[i], i);
            }
        }
        int currentCity = 0;
        findMaxCitiesTravel(g.adj, currentCity, a).stream().sorted().collect(Collectors.toList()).forEach(p -> System.out.print(p + " "));
    }


    private static LinkedList<Integer> findMaxCitiesTravel(LinkedList[] adj, int cur, int[] a) {

        int dis[] = new int[adj.length];
        Map<Integer, LinkedList<Integer>> path = new HashMap<Integer, LinkedList<Integer>>();
        int track[] = new int[adj.length];


        Queue<Integer> queue = new LinkedList<>();
        queue.add(cur);
        dis[cur] = 0;

        while (!queue.isEmpty()) {

            int p = queue.poll();

            for (int i = 0; i < adj[p].size(); i++) {

                dis[(Integer) adj[p].get(i)] = dis[p] + 1;

                queue.add((Integer) adj[p].get(i));

                if (!path.containsKey((Integer) adj[p].get(i))) {

                    LinkedList<Integer> pathNodes = new LinkedList<>();
                    int node = (Integer) adj[p].get(i);
                    pathNodes.add(node);
                    if (node % 2 == 1) {
                        track[(Integer) adj[p].get(i)] = track[(Integer) adj[p].get(i)] + 1;
                    }
                    while (node != cur && track[(Integer) adj[p].get(i)] <= 1) {
                        pathNodes.add(a[node]);
                        node = a[node];
                        if (node % 2 == 1) {
                            track[(Integer) adj[p].get(i)] = track[(Integer) adj[p].get(i)] + 1;
                        }
                    }
                    path.put((Integer) adj[p].get(i), pathNodes);
                }
            }
        }

        return largestpathcontainsZero(path, cur);
    }

    static LinkedList<Integer> largestpathcontainsZero(Map<Integer, LinkedList<Integer>> path, int cur) {

        AtomicInteger max = new AtomicInteger(0);
        AtomicInteger node = new AtomicInteger(-1);

        path.entrySet().stream().forEach(e -> {
            if (e.getValue().size() > max.get() && e.getValue().contains(cur)) {
                max.set(e.getValue().size());
                node.set(e.getKey());
            }
        });


        return path.get(node.get());
    }

    static class Graph {
        int V;
        LinkedList[] adj;

        Graph(int vertices) {
            this.V = vertices;
            adj = new LinkedList[V];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new LinkedList();
            }
        }

        void addEdge(int s, int d) {
            adj[s].add(d);
        }
    }
}
