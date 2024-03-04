package ru.krisnovitskaya.algorithms.sprint6_graf.J_topological_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class JTopologicalSort {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Map<Integer, Set<Integer>> map = fillMap(reader, vertexCount, edgeCount);

            List<Integer> result = mainDFS(vertexCount, map);
            StringBuilder sb = new StringBuilder();
            for (int i = result.size() -1 ; i >= 0; i--) {
                sb.append(result.get(i)).append(" ");
            }
            writer.write(sb.toString());
        }
    }

    private static List<Integer> mainDFS(int vertexCount, Map<Integer, Set<Integer>> map) {
        String[] color = new String[vertexCount + 1];

        List<Integer> result = new ArrayList<>();
        for (int i = vertexCount; i >= 1; i--){
            if(color[i] == null){
                DFS(i, map, color, result);
            }
        }
        return result;
    }



    private static void DFS(int startVertex, Map<Integer, Set<Integer>> map, String[] color, List<Integer> result) {

        color[startVertex] = "gray"; // Вершина посещена, но ещё не обработана.
        Set<Integer> outgoingEdges = outgoingEdges(map, startVertex); // Получите список исходящих ребер в зависимости от способа хранения графа
        for (int w : outgoingEdges) {
            if (color[w] == null) { // Если вершина не посещена, то
                DFS(w, map, color, result); // запустим обход от найденной смежной вершины.
            }
        }
        color[startVertex] = "black"; // Теперь вершина обработана.
        result.add(startVertex); // Теперь вершина обработана.
    }

    private static Set<Integer> outgoingEdges(Map<Integer, Set<Integer>> map, int index){
        return Optional.ofNullable(map.get(index)).orElse(Set.of());
    }



    private static Map<Integer, Set<Integer>> fillMap(BufferedReader reader, int vertexCount, int edgeCount) throws IOException {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= edgeCount; i++) {
            String[] s = reader.readLine().split(" ", 2);
            int startVertex = Integer.parseInt(s[0]);
            int endVertex = Integer.parseInt(s[1]);
            Set<Integer> set = new TreeSet<>(Comparator.reverseOrder());
            set.add(endVertex);
            map.merge(startVertex, set, (old, newV) -> {
                old.add(endVertex);
                return old;
            });
        }
        return map;
    }
}
