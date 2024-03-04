package ru.krisnovitskaya.algorithms.sprint6_graf.G_max_distance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GMaxDistance {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Map<Integer, Set<Integer>> map = fillMapGraf(reader, vertexCount, edgeCount);

            int startVertex = Integer.parseInt(reader.readLine());
            Integer result = maxDistance(startVertex, map, vertexCount);

            writer.write(result.toString());
        }
    }


    private static Integer maxDistance(int startVertex, Map<Integer, Set<Integer>> map, int vertexCount) {
        String[] color = new String[vertexCount + 1];
        List<Integer> distance = new ArrayList<>();
        for (int i = 0; i <= vertexCount; i++) {
            distance.add(null);
        }
        // Создадим очередь вершин и положим туда стартовую вершину.
        Queue<Integer> planned = new LinkedList<>();
        planned.add(startVertex);
        color[startVertex] = "gray";
        distance.set(startVertex, 0);

        while (!planned.isEmpty()) {
            int u = planned.poll();  // Возьмём вершину из очереди.

            for (int v : outgoingEdges(map, u)) {
                if (color[v] == null ) {
                    // Серые и чёрные вершины уже
                    // либо в очереди, либо обработаны.
                    color[v] = "gray";
                    planned.add(v);  // Запланируем посещение вершины.
                    distance.set(v, distance.get(u) + 1);
                }
            }
            color[u] ="black";  // Теперь вершина считается обработанной.
        }
        distance.sort(Comparator.nullsLast(Comparator.reverseOrder()));
        return distance.get(0);
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
