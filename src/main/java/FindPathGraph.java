import java.util.LinkedList;
import java.util.Queue;

public class FindPathGraph {

    public static void main(String[] args) {
        //  0------------1
        //  |  \         |
        //  |     \      |
        //  |         \  |
        // 3-------------2

        // Create a sample graph
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);

        // arbitrary source
        int s = 2;

        // arbitrary destination
        int d = 3;

        System.out.println(
                "Following are all different paths from "
                        + s + " to " + d);

        FindPathGraph f = new FindPathGraph();
        f.printAllPaths(s, d, g);
    }

    private void printAllPaths(int s, int d, Graph g) {

        LinkedList paths = new LinkedList<Integer>();
        paths.add(s);
        boolean[] isVisited = new boolean[g.V];
        printUtilPath(s, d, g, paths, isVisited);

    }

    private void printUtilPath(int s, int d, Graph g, LinkedList paths, boolean[] isVisited) {

        if (s == d) {
            paths.forEach(p -> System.out.print(p + " "));
            System.out.println();
            return;
        }

        isVisited[s] = true;

        for (int i = 0; i < g.adj[s].size(); i++) {
            if (!isVisited[(int) g.adj[s].get(i)]) {
                paths.add(g.adj[s].get(i));
                printUtilPath((Integer) g.adj[s].get(i), d, g, paths, isVisited);
                paths.remove(g.adj[s].get(i));
            }
        }

        isVisited[s] = false;
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
