import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FindLongestPathUnGraph {

    public static void main(String[] args) {
        // Create a sample graph
        FindLongestPathUnGraph.Graph g = new FindLongestPathUnGraph.Graph(12);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 5);
        g.addEdge(1, 7);
        g.addEdge(2, 8);
        g.addEdge(3, 4);
        g.addEdge(4, 9);
        g.addEdge(5, 10);
        g.addEdge(10, 11);

        FindLongestPathUnGraph f = new FindLongestPathUnGraph();
        System.out.print(f.longestPath(g,0));
    }



    private int longestPath(FindLongestPathUnGraph.Graph g, int s) {

        LinkedList[] adj = g.adj;
        int dis[] = new int[adj.length];
        Arrays.fill(dis,-1);

        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        dis[0] = 0;
        int longest = 0;
        while (!queue.isEmpty()){

            int current = queue.poll();

            for (int i = 0; i < adj[current].size(); i++) {
                dis[(Integer) adj[current].get(i)] = dis[current] + 1;
                queue.add((Integer) adj[current].get(i));
                longest = Math.max(longest, dis[(Integer) adj[current].get(i)]);
            }
        }
        return longest;
    }

    public static class Graph {
        int V;
        LinkedList[] adj;

        Graph(int v) {
            this.V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new LinkedList<Integer>();
            }
        }


        public void addEdge(int s, int d) {
            adj[s].add(d);
        }
    }


}
