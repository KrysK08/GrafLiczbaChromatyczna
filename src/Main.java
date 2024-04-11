import java.util.ArrayList;
import java.util.List;
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
    public void dijkstra(int startVertex) {
        int[] distance = new int[adjacencyList.size()];
        boolean[] visited = new boolean[adjacencyList.size()];
        for (int i = 0; i < adjacencyList.size(); i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distance[startVertex] = 0;
        for (int i = 0; i < adjacencyList.size() - 1; i++) {
            int minVertex = findMinVertex(distance, visited);
            visited[minVertex] = true;
            List<Edge> edges = adjacencyList.get(minVertex);
            for (Edge edge : edges) {
                if (!visited[edge.destination] && distance[minVertex] != Integer.MAX_VALUE &&
                        distance[minVertex] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[minVertex] + edge.weight;
                }
            }
        }
        printDistances(distance);
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
        int startVertex = 0;
        graph.dijkstra(startVertex);
    }
}