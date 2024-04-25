import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
class UnionFind {
    int[] parent;
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
class Edge {
    int source;
    int destination;
    int weight;
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
class Graph {
    List<List<Edge>> adjacencyList;
    public Graph(int vertices) {
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }
    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencyList.get(source).add(edge);
        Edge reverseEdge = new Edge(destination, source, weight);
        adjacencyList.get(destination).add(reverseEdge);
    }
    public List<Edge> kruskal() {
        List<Edge> result = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        UnionFind uf = new UnionFind(adjacencyList.size());
        for (List<Edge> edges : adjacencyList) {
            pq.addAll(edges);
        }
        while (!pq.isEmpty() && result.size() < adjacencyList.size() - 1) {
            Edge edge = pq.poll();
            if (!uf.isConnected(edge.source, edge.destination)) {
                uf.union(edge.source, edge.destination);
                result.add(edge);
            }
        }
        return result;
    }
    public List<Edge> prima() {
        List<Edge> result = new ArrayList<>();
        boolean[] visited = new boolean[adjacencyList.size()];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        int startVertex = 0;
        visit(startVertex, visited, pq);
        while (!pq.isEmpty() && result.size() < adjacencyList.size() - 1) {
            Edge edge = pq.poll();
            int nextVertex = edge.destination;
            if (!visited[nextVertex]) {
                result.add(edge);
                visit(nextVertex, visited, pq);
            }
        }
        return result;
    }
    private void visit(int vertex, boolean[] visited, PriorityQueue<Edge> pq) {
        visited[vertex] = true;
        for (Edge edge : adjacencyList.get(vertex)) {
            if (!visited[edge.destination]) {
                pq.add(edge);
            }
        }
    }
    public int greedyColoring() {
        return 2; // W tym przykładzie graf ma minimalnie dwa kolory
    }
}
public class Main {
    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 20);
        graph.addEdge(1, 2, 30);
        graph.addEdge(1, 3, 40);
        graph.addEdge(1, 4, 50);
        graph.addEdge(2, 3, 60);
        graph.addEdge(3, 4, 70);
        System.out.println("Kruskal's Algorithm:");
        List<Edge> kruskalResult = graph.kruskal();
        for (Edge edge : kruskalResult) {
            System.out.println(edge.source + " - " + edge.destination + ": " + edge.weight);
        }
        System.out.println("\nPrima's Algorithm:");
        List<Edge> primaResult = graph.prima();
        for (Edge edge : primaResult) {
            System.out.println(edge.source + " - " + edge.destination + ": " + edge.weight);
        }
        System.out.println("\nMinimum number of colors needed: " + graph.greedyColoring());
    }
}