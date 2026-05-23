# Graph Algorithms — Bonus Task: Dijkstra's Shortest Path

## Overview

This bonus task extends the existing graph project with **Dijkstra's Algorithm** to compute the shortest path from a starting vertex to every other vertex in a weighted graph.

---

## What Was Added / Modified

| File | Change |
|------|--------|
| `Edge.java` | Added `weight` field, getter, and updated `toString()` |
| `Graph.java` | Switched adjacency list to `List<Edge>`; added `addUndirectedEdge()`; implemented `dijkstra(int start)` |
| `Main.java` | Three demo scenarios covering directed, undirected, and unreachable-vertex cases |

---

## Class Design

### `Edge.java`
```
Edge
├── int destination   // target vertex
├── int weight        // cost of traversal  ← NEW
├── getDestination()
└── getWeight()       ← NEW
```

### `Graph.java`
```
Graph
├── int vertices
├── List<List<Edge>> adjacencyList   // weighted adjacency list
├── addEdge(src, dest, weight)       // directed edge
├── addUndirectedEdge(src, dest, weight)
├── dijkstra(int start)              ← MAIN NEW METHOD
├── printGraph()
└── (private) minDistance(), buildPath(), printResults()
```

---

## Algorithm — How It Works

Dijkstra's algorithm finds the shortest path from one source vertex to all others in a graph with **non-negative edge weights**.

### Steps:

1. **Initialise** — set `dist[start] = 0`, all others to `∞`. Mark all vertices unvisited.
2. **Pick** the unvisited vertex `u` with the smallest known distance (linear scan = O(V)).
3. **Relax** — for every neighbour `v` of `u`:  
   if `dist[u] + weight(u→v) < dist[v]`, update `dist[v]` and record `prev[v] = u`.
4. **Mark** `u` as visited (its distance is now final).
5. **Repeat** steps 2–4 until all reachable vertices are visited.
6. **Reconstruct** paths using the `prev[]` array.

### Complexity
| | Value |
|---|---|
| Time | O(V²) — linear scan for min-distance |
| Space | O(V + E) — dist[], visited[], prev[], adjacency list |

No priority queue is used — distance arrays and simple loops only, as required.

---

## Sample Output

### Example 1 — Directed Graph (6 vertices)

```
--- Adjacency List ---
Vertex 0: -> 1 (weight: 1)  -> 2 (weight: 4)
Vertex 1: -> 3 (weight: 2)  -> 4 (weight: 3)
Vertex 2: -> 4 (weight: 2)
Vertex 3:
Vertex 4: -> 5 (weight: 5)
Vertex 5: -> 3 (weight: 1)

===========================================
  Dijkstra's Algorithm — Source: 0
===========================================
Vertex     Distance     Path
-------------------------------------------
0          0            0
1          1            0 -> 1
2          4            0 -> 2
3          3            0 -> 1 -> 3
4          4            0 -> 1 -> 4
5          9            0 -> 1 -> 4 -> 5
===========================================
```

### Example 2 — Undirected Graph (5 vertices)

```
===========================================
  Dijkstra's Algorithm — Source: 0
===========================================
Vertex     Distance     Path
-------------------------------------------
0          0            0
1          6            0 -> 1
2          1            0 -> 2
3          11           0 -> 1 -> 3
4          10           0 -> 2 -> 4
===========================================
```

### Example 3 — Graph with Unreachable Vertex

```
===========================================
  Dijkstra's Algorithm — Source: 0
===========================================
Vertex     Distance     Path
-------------------------------------------
0          0            0
1          3            0 -> 1
2          5            0 -> 1 -> 2
3          Unreachable  No path
===========================================
```

---

## How to Run

```bash
# Compile
cd src
javac Edge.java Graph.java Main.java

# Run
java Main
```

Requires **Java 8** or higher.

---

## Key Design Decisions

- **Adjacency list with `Edge` objects** — efficient for sparse graphs and naturally stores weight alongside destination.
- **`prev[]` array** — enables full path reconstruction without extra data structures.
- **`Integer.MAX_VALUE` for infinity** — standard sentinel; guarded against overflow in the relaxation step.
- **`addUndirectedEdge()`** — adds edges in both directions so the same `Graph` class handles directed and undirected graphs.
- **Separate `printResults()` / `buildPath()`** — keeps `dijkstra()` focused on the algorithm logic only.
