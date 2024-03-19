package ru.krisnovitskaya.algorithms.sprint6_graf.K_attarctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class KAttractions {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Graph graph = fillGraph(reader, vertexCount, edgeCount);
            int[][] matrix = graph.countingDistancesMatrix();

            System.out.println(printMatrix(matrix));
            writer.print(printMatrix(matrix));
        }
    }

    private static String printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    private static Graph fillGraph(BufferedReader reader, int vertexCount, int edgeCount) throws IOException {
        Graph graph = new Graph(vertexCount, edgeCount);
        for (int i = 1; i <= edgeCount; i++) {
            graph.addEdge(reader.readLine());
        }
        return graph;
    }


    static class Graph { //неориентированный взвешенный граф
        private int vertexCount;
        private int edgeCount;
        private Map<Integer, List<Edge>> outgoingEdges;

        private Integer[] dist;
        private Integer[] previous;
        private Boolean[] visited;


        public Graph(int vertexCount, int edgeCount) {
            this.vertexCount = vertexCount;
            this.edgeCount = edgeCount;
            this.outgoingEdges = new HashMap<>();
            this.dist = new Integer[vertexCount + 1];
            this.previous = new Integer[vertexCount + 1];
            this.visited = new Boolean[vertexCount + 1];
        }

        //
        public void addEdge(String input) {
            String[] s = input.split(" ", 3);
            Integer startVertex = Integer.parseInt(s[0]);
            Integer endVertex = Integer.parseInt(s[1]);
            Integer weight = Integer.parseInt(s[2]);
            outgoingEdges.merge(startVertex, new ArrayList<>(List.of(new Edge(endVertex, weight))), (old, newV) -> {
                old.add(new Edge(endVertex, weight));
                return old;
            });

            outgoingEdges.merge(endVertex, new ArrayList<>(List.of(new Edge(startVertex, weight))), (old, newV) -> {
                old.add(new Edge(startVertex, weight));
                return old;
            });
        }

        private Integer getMinDistNotVisitedVertex() {

            Integer currentMinimum = Integer.MAX_VALUE;
            Integer currentMinimumVertex = null;

            for (int v = 1; v <= vertexCount; v++) {
                if (!visited[v] && dist[v] < currentMinimum) {
                    currentMinimum = dist[v];
                    currentMinimumVertex = v;
                }
            }
            return currentMinimumVertex;
        }

        public int[][] countingDistancesMatrix() {
            int[][] matrix = new int[vertexCount][vertexCount];
            for (int i = 1; i <= vertexCount; i++) {
                dijkstra(i);
                for (int j = 1; j <= vertexCount; j++) {
                    matrix[i - 1][j - 1] = (dist[j] == Integer.MAX_VALUE) ? -1 : dist[j];
                }
            }
            return matrix;
        }


        private void dijkstra(Integer s) {

            for (int v = 1; v <= vertexCount; v++) {
                dist[v] = Integer.MAX_VALUE;
                previous[v] = null;
                visited[v] = false;
            }

            dist[s] = 0;

            while (true) {
                Integer u = getMinDistNotVisitedVertex();

                if (u == null || dist[u] == Integer.MAX_VALUE) {
                    break;
                }

                visited[u] = true;

                List<Edge> neighbours = outgoingEdges(u);

                for (Edge neighbour : neighbours) {
                    relax(u, neighbour);
                }
            }
        }

        private void relax(Integer curVertex, Edge v) {
            if (dist[v.getNumberTo()] > dist[curVertex] + v.getWeight()) {
                dist[v.getNumberTo()] = dist[curVertex] + v.getWeight();
                previous[v.getNumberTo()] = curVertex;
            }
        }

        private List<Edge> outgoingEdges(Integer u) {
            return Optional.ofNullable(outgoingEdges.get(u)).orElse(List.of());
        }
    }

    static class Edge {
        private Integer numberTo;
        private Integer weight;

        public Edge(Integer numberTo, Integer weight) {
            this.numberTo = numberTo;
            this.weight = weight;
        }

        public Integer getNumberTo() {
            return numberTo;
        }

        public Integer getWeight() {
            return weight;
        }
    }
}
