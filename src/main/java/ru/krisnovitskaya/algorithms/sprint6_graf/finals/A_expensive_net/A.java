package ru.krisnovitskaya.algorithms.sprint6_graf.finals.A_expensive_net;

// https://contest.yandex.ru/contest/25070/run-report/109554237/

/* -- ПРИНЦИП РАБОТЫ --
Алгоритм работы представляет из себя:
1)создание и наполнение графа. Так как граф неориентированный ребра добавляются х2.
2) применение модифицированного алгоритма прима для поиска максимального остовного дерева (МкОД).
3) Вычисление суммы ребер МкОД
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
V - количество вершин (на входе),
E - количество ребер (на входе)

Временная сложность создания и наполнения графа:
Создание графа с вершинами O(V) + наполнение ребрами O(E) -> O(E+V)
Временная сложность алгоритма прима с графами на основе списков смежности и бинарной кучей для хранения ребер
 равна O(ElogV) (Поиск в списках вершин добавленные/не добавленные происходит за O(1))

Количество ребер в МкОД примерно равно количеству вершин, поэтому сложность суммы будет O(V)
Итого полная временная сложность алгоритма: O(E+V) + O(ElogV) + O(V) -> O(E + V + ElogV)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
Хранение графа с вершинами O(V) + и ребрами O(E) -> O(E+V)
Алгорим прима:
сет not_added = O(V)
бинарная куча ребер в максимуме O(E)
Список ребер МкОД в максимуме примерно O(V)

Итого общая пространственная сложность O(E+V) + O(V) + O(E) + O(V) -> O(2E + 3V) -> O(E+V)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
A. Дорогая сеть
 */
public class A {
    private static String OOPS = "Oops! I did it again";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Graph<Integer> graph = initializeGraph(vertexCount);
            addEdges(reader, edgeCount, graph);

            String result = countExpensiveNet(graph);
            writer.println(result);
        }
    }

    private static String countExpensiveNet(Graph<Integer> graph) {
        Set<Integer> notAdded = new HashSet<>(graph.getAllVertices());
        Queue<Graph.Edge<Integer>> edges = new PriorityQueue<>();
        List<Graph.Edge<Integer>> resultEdges = new ArrayList<>();

        Integer first = graph.getAllVertices().stream().findFirst().get();

        addVertex(graph,notAdded, edges, first);
        while (!notAdded.isEmpty() && !edges.isEmpty()) {
            Graph.Edge<Integer> edge = edges.poll();
            if(notAdded.contains(edge.getLabelTo())){
                resultEdges.add(edge);
                addVertex(graph, notAdded, edges, edge.getLabelTo());
            }
        }

        if (!notAdded.isEmpty()) {
            return OOPS;
        } else {
            return resultEdges.stream().map(Graph.Edge::getWeight).reduce(0, Integer::sum).toString();
        }
    }

    private static void addVertex(Graph<Integer> graph, Set<Integer> notAdded, Queue<Graph.Edge<Integer>> edges, Integer vertex) {
        notAdded.remove(vertex);
        for (Graph.Edge<Integer> outgoingEdge : graph.outgoingEdges(vertex)) {
            if (notAdded.contains(outgoingEdge.getLabelTo())) {
                edges.add(outgoingEdge);
            }
        }
    }

    private static void addEdges(BufferedReader reader, int edgeCount, Graph<Integer> graph) throws IOException {
        for (int i = 1; i <= edgeCount; i++) {
            String[] s = reader.readLine().split(" ", 3);
            Integer labelFrom = Integer.parseInt(s[0]);
            Integer labelTo = Integer.parseInt(s[1]);
            Integer weight = Integer.parseInt(s[2]);
            graph.addEdge(labelFrom, labelTo, weight);
            graph.addEdge(labelTo, labelFrom, weight);
        }
    }

    private static Graph<Integer> initializeGraph(int vertexCount) {
        Graph<Integer> graph = new Graph<>();
        for (int i = 1; i <= vertexCount; i++) {
            graph.addVertex(i);
        }
        return graph;
    }

    static class Graph<L> {
        Map<L, List<Edge<L>>> map;

        public Graph() {
            map = new HashMap<>();
        }

        public void addVertex(L label) {
            map.put(label, new ArrayList<>());
        }

        public void addEdge(L labelFrom, L labelTo, Integer weight) {
            map.computeIfPresent(labelFrom, (oldV, newV) -> {
                newV.add(new Edge<>(labelFrom, labelTo, weight));
                return newV;
            });
        }

        public Set<L> getAllVertices() {
            return map.keySet();
        }

        public List<Edge<L>> outgoingEdges(L labelFrom) {
            return map.get(labelFrom);
        }

        static class Edge<L> implements Comparable<Edge> {
            L labelFrom;
            L labelTo;
            Integer weight;

            public Edge(L labelFrom, L labelTo, Integer weight) {
                this.labelFrom = labelFrom;
                this.labelTo = labelTo;
                this.weight = weight;
            }

            public L getLabelFrom() {
                return labelFrom;
            }

            public L getLabelTo() {
                return labelTo;
            }

            public Integer getWeight() {
                return weight;
            }

            @Override
            public int compareTo(Edge o) {
                return -Integer.compare(this.weight, o.getWeight());
            }
        }
    }
}
