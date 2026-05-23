import java.util.ArrayList;
import java.util.List;

/**
 * Weighted directed graph using an adjacency list.
 * Supports Dijkstra's shortest-path algorithm.
 */
public class Graph {
    private int vertices;
    private List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    /**
     * Adds a directed weighted edge from src to dest.
     */
    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Edge(dest, weight));
    }

    /**
     * Adds an undirected weighted edge between src and dest.
     */
    public void addUndirectedEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Edge(dest, weight));
        adjacencyList.get(dest).add(new Edge(src, weight));
    }

    /**
     * Dijkstra's Algorithm — finds shortest distances from the start vertex
     * to all other vertices using arrays and simple loops (no priority queue).
     *
     * Time complexity: O(V^2) with this approach.
     *
     * @param start the source vertex index
     */
    public void dijkstra(int start) {
        int[] dist = new int[vertices];      // shortest known distance from start
        boolean[] visited = new boolean[vertices]; // whether vertex is finalized
        int[] prev = new int[vertices];      // predecessor for path reconstruction

        // Initialize distances to "infinity"
        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
            prev[i] = -1;
        }
        dist[start] = 0;

        // Relax edges V times
        for (int count = 0; count < vertices; count++) {
            // Pick the unvisited vertex with the smallest known distance
            int u = minDistance(dist, visited);
            if (u == -1) break; // remaining vertices are unreachable

            visited[u] = true;

            // Update distances for all neighbours of u
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.getDestination();
                int w = edge.getWeight();

                if (!visited[v] && dist[u] != Integer.MAX_VALUE
                        && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                }
            }
        }

        printResults(start, dist, prev);
    }

    /**
     * Returns the index of the unvisited vertex with the minimum distance.
     */
    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    /**
     * Prints shortest distances and the path taken to reach each vertex.
     */
    private void printResults(int start, int[] dist, int[] prev) {
        System.out.println("===========================================");
        System.out.println("  Dijkstra's Algorithm — Source: " + start);
        System.out.println("===========================================");
        System.out.printf("%-10s %-12s %s%n", "Vertex", "Distance", "Path");
        System.out.println("-------------------------------------------");

        for (int i = 0; i < vertices; i++) {
            String distStr = (dist[i] == Integer.MAX_VALUE) ? "Unreachable" : String.valueOf(dist[i]);
            String path = buildPath(start, i, prev);
            System.out.printf("%-10d %-12s %s%n", i, distStr, path);
        }
        System.out.println("===========================================");
    }

    /**
     * Reconstructs the path from start to target using the prev array.
     */
    private String buildPath(int start, int target, int[] prev) {
        if (prev[target] == -1 && target != start) return "No path";

        StringBuilder sb = new StringBuilder();
        int current = target;

        while (current != -1) {
            sb.insert(0, current);
            if (prev[current] != -1) sb.insert(0, " -> ");
            current = prev[current];
        }
        return sb.toString();
    }

    /**
     * Prints the current adjacency list for inspection.
     */
    public void printGraph() {
        System.out.println("\n--- Adjacency List ---");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (Edge e : adjacencyList.get(i)) {
                System.out.print(e + "  ");
            }
            System.out.println();
        }
        System.out.println("----------------------\n");
    }
}
