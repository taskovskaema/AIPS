
import java.util.*;
public class Facebook {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();

        Set<String> nodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine().trim();
            graph.addVertex(str);
            nodes.add(str);
        }

        int m = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < m; i++) {
            String[] str = scanner.nextLine().split("\\s+");
            String node1 = str[0];
            String node2 = str[1];

            graph.addEdge(node1, node2);
        }

        scanner.close();

        int brojac = 0;
        Set<String> visited = new HashSet<>();
        for (String node : nodes) {
            if (!visited.contains(node)) {
                graph.DFSUtil(node, visited);
                brojac++;
            }
        }
        System.out.println(brojac);
    }

    static class AdjacencyListGraph<T> {
        private Map<T, Set<T>> adjacencyList;

        public AdjacencyListGraph() {
            this.adjacencyList = new HashMap<>();
        }

        public void addVertex(T vertex) {
            adjacencyList.putIfAbsent(vertex, new HashSet<>());
        }

        public void addEdge(T source, T destination) {
            addVertex(source);
            addVertex(destination);
            adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source);
        }

        public Set<T> getNeighbors(T vertex) {
            return adjacencyList.getOrDefault(vertex, new HashSet<>());
        }

        private void DFSUtil(T vertex, Set<T> visited) {
            visited.add(vertex);
            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    DFSUtil(neighbor, visited);
                }
            }
        }
    }
}
