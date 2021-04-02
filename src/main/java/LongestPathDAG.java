import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LongestPathDAG {

    public static void main(String[] args) {

        Graph graph = new Graph(6);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 4, 6);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 5, 4);
        graph.addEdge(3, 6, 2);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 6, 1);
        graph.addEdge(4, 5, -1);
        graph.addEdge(5, 6, -2);
        graph.findLongestPath(2);
    }

    private static class Graph {
        int vertex;
        LinkedList[] adj;

        public Graph(int vertex) {
            this.vertex = vertex;
            adj = new LinkedList[vertex+1];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new LinkedList<Edge>();
            }
        }

        public void addEdge(int s, int d, int w) {
            adj[s].add(new Edge(d, w));
        }

        void findLongestPath(int s) {

            int cost[] = new int[vertex + 1];

            Arrays.fill(cost, -1);

            cost[s] = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);

            while (!queue.isEmpty()) {

                int t = queue.poll();

                for (int i = 0; i < adj[t].size(); i++) {
                    Edge edge = (Edge) adj[t].get(i);

                    if (cost[edge.d] == -1) {
                        queue.add(edge.d);
                        cost[edge.d] = cost[t] + edge.cost;
                    } else {
                        if (cost[edge.d] < cost[t] + edge.cost) {
                            queue.add(edge.d);
                            cost[edge.d] = cost[t] + edge.cost;
                        }
                    }

                }

            }

            for (int i = 1; i < cost.length; i++) {
                System.out.println("Longest path from " + s + " to " + i + " is - " + cost[i]);
            }


        }

        private class Edge {
            int d;
            int cost;

            Edge(int d, int cost) {
                this.d = d;
                this.cost = cost;
            }
        }
    }
}
