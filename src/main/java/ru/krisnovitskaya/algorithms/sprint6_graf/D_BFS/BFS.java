package ru.krisnovitskaya.algorithms.sprint6_graf.D_BFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class BFS {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Map<Integer, Set<Integer>> map = fillMapGraf(reader, vertexCount, edgeCount);

            int startVertex = Integer.parseInt(reader.readLine());
            List<Integer> result = BFS(startVertex, map, new String[vertexCount + 1]);

//            System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
            writer.write(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        }
    }


    private static List<Integer> BFS(int startVertex, Map<Integer, Set<Integer>> map, String[] color) {
        List<Integer> result = new ArrayList<>();

        // Создадим очередь вершин и положим туда стартовую вершину.
        Queue<Integer> planned = new LinkedList<>();
        planned.add(startVertex);
        color[startVertex] = "gray";

        while (!planned.isEmpty()) {
            int u = planned.poll();  // Возьмём вершину из очереди.

            for (int v : outgoingEdges(map, u)) {
                if (color[v] == null ) {
                    // Серые и чёрные вершины уже
                    // либо в очереди, либо обработаны.
                    color[v] = "gray";
                    planned.add(v);  // Запланируем посещение вершины.
                }
            }
            color[u] ="black";  // Теперь вершина считается обработанной.
            result.add(u);
        }
        return result;
    }

    private static Set<Integer> outgoingEdges(Map<Integer, Set<Integer>> map, int index){
        return Optional.ofNullable(map.get(index)).orElse(Set.of());
    }



    private static Map<Integer, Set<Integer>> fillMapGraf(BufferedReader reader, int vertexCount, int edgeCount) throws IOException {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= edgeCount; i++) {
            String[] s = reader.readLine().split(" ", 2);
            int startVertex = Integer.parseInt(s[0]);
            int endVertex = Integer.parseInt(s[1]);
            Set<Integer> set = new TreeSet<>(Comparator.naturalOrder());
            set.add(endVertex);
            map.merge(startVertex, set, (old, newV) -> {
                old.add(endVertex);
                return old;
            });
            Set<Integer> set2 = new TreeSet<>(Comparator.naturalOrder());
            set2.add(startVertex);
            map.merge(endVertex, set2, (old, newV) -> {
                old.add(startVertex);
                return old;
            });
        }
        return map;
    }
}
