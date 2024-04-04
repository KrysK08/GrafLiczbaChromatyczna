import java.util.*;
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
    public void printGraph() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            List<Edge> edges = adjacencyList.get(i);
            System.out.print("Vertex " + i + " is connected to: ");
            for (Edge edge : edges) {
                System.out.print(edge.destination + " (Weight: " + edge.weight + ") ");
            }
            System.out.println();
        }
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
        graph.printGraph();
    }
}