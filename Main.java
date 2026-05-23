/**
 * Entry point — demonstrates Dijkstra's algorithm on two example graphs.
 */
public class Main {
    public static void main(String[] args) {

        // -------------------------------------------------------
        // Example 1: Classic textbook graph (6 vertices)
        //
        //        1       2
        //   0 -----> 1 -----> 3
        //   |        |        ^
        // 4 |      3 |      1 |
        //   v        v        |
        //   2 -----> 4 -----> 5
        //        2       5
        // -------------------------------------------------------
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         EXAMPLE 1 (Directed)         ║");
        System.out.println("╚══════════════════════════════════════╝");

        Graph g1 = new Graph(6);
        g1.addEdge(0, 1, 1);
        g1.addEdge(0, 2, 4);
        g1.addEdge(1, 3, 2);
        g1.addEdge(1, 4, 3);
        g1.addEdge(2, 4, 2);
        g1.addEdge(4, 5, 5);
        g1.addEdge(5, 3, 1);

        g1.printGraph();
        g1.dijkstra(0);

        // -------------------------------------------------------
        // Example 2: Undirected weighted graph (5 vertices)
        //
        //   0 --6-- 1 --5-- 3
        //   |  \         /
        //   1    14    15
        //   |      \  /
        //   2 --9-- 4
        // -------------------------------------------------------
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        EXAMPLE 2 (Undirected)        ║");
        System.out.println("╚══════════════════════════════════════╝");

        Graph g2 = new Graph(5);
        g2.addUndirectedEdge(0, 1, 6);
        g2.addUndirectedEdge(0, 2, 1);
        g2.addUndirectedEdge(0, 4, 14);
        g2.addUndirectedEdge(1, 3, 5);
        g2.addUndirectedEdge(2, 4, 9);
        g2.addUndirectedEdge(3, 4, 15);

        g2.printGraph();
        g2.dijkstra(0);

        // -------------------------------------------------------
        // Example 3: Graph with an unreachable vertex
        // -------------------------------------------------------
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║    EXAMPLE 3 (Unreachable Vertex)    ║");
        System.out.println("╚══════════════════════════════════════╝");

        Graph g3 = new Graph(4);
        g3.addEdge(0, 1, 3);
        g3.addEdge(1, 2, 2);
        // vertex 3 is isolated — no edges lead to it

        g3.printGraph();
        g3.dijkstra(0);
    }
}
