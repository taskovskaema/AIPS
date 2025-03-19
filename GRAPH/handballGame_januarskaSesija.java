import java.util.*;

class WeightedGraph<T> {
    private Map<T, List<Edge<T>>> adjacencyList;

    public WeightedGraph() {
        this.adjacencyList = new HashMap<>();
    }


    static class Edge<T> {
        T destination;
        int weight;

        Edge(T destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }


    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }


    public void addEdge(T source, T destination, int weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(new Edge<>(destination, weight));
    }


    public List<Edge<T>> getEdges(T vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }


    public int shortestPath(T start, T goal) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<NodeDistance<T>> pq = new PriorityQueue<>();
        int max = 1000000;


        for (T node : adjacencyList.keySet()) {
            distances.put(node, max);
        }
        distances.put(start, 0);
        pq.add(new NodeDistance<>(start, 0));

        while (!pq.isEmpty()) {
            NodeDistance<T> current = pq.poll();
            T currentNode = current.node;
            int currentDist = current.distance;

            if (currentNode.equals(goal)) {
                return currentDist;
            }

            if (currentDist > distances.get(currentNode)) {
                continue;
            }

            for (Edge<T> edge : getEdges(currentNode)) {
                T neighbor = edge.destination;
                int newDist = currentDist + edge.weight;

                if (newDist < distances.getOrDefault(neighbor, max)) {
                    distances.put(neighbor, newDist);
                    pq.add(new NodeDistance<>(neighbor, newDist));
                }
            }
        }

        return distances.getOrDefault(goal, max);
    }


    static class NodeDistance<T> implements Comparable<NodeDistance<T>> {
        T node;
        int distance;

        NodeDistance(T node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance<T> other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}

public class RakometenNatprevar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WeightedGraph<String> graph = new WeightedGraph<>();

        int M = input.nextInt();
        input.nextLine();


        for (int i = 0; i < M; i++) {
            String[] parts = input.nextLine().split(" ");
            String player1 = parts[0] + "_" + parts[1];
            String team1 = parts[1];
            String player2 = parts[2] + "_" + parts[3];
            String team2 = parts[3];
            int vreme = Integer.parseInt(parts[4]);

            if (team1.equals(team2)) {
                graph.addEdge(player1, player2, vreme);
            }
        }

        int K = input.nextInt();
        input.nextLine();


        for (int i = 0; i < K; i++) {
            String[] parts = input.nextLine().split(" ");
            String shutira = parts[0] + "_" + parts[1];
            int time = Integer.parseInt(parts[2]);

            graph.addEdge(shutira, "Goal", time);
        }


        String[] startParts = input.nextLine().split(" ");
        String startNode = startParts[0] + "_" + startParts[1];


        int minTime = graph.shortestPath(startNode, "Goal");


        System.out.println(minTime);
    }
}
