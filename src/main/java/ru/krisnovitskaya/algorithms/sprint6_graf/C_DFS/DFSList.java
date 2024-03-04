package ru.krisnovitskaya.algorithms.sprint6_graf.C_DFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class DFSList {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Map<Integer, Set<Integer>> map = fillMap(reader, vertexCount, edgeCount);

            int startIndex = Integer.parseInt(reader.readLine());
            Set<Integer> dfs = mainDFS(startIndex, vertexCount, map);

            writer.write(dfs.stream().map(integer -> integer.toString()).collect(Collectors.joining(" ")));
//            System.out.println((dfs.stream().map(integer -> integer.toString()).collect(Collectors.joining(" "))));

        }
    }

    private static Set<Integer> mainDFS(int startIndex, int vertexCount, Map<Integer, Set<Integer>> map) {
        List<String> color = new ArrayList<>();
        color.add(null);
        for (int i = 1; i <= vertexCount; i++) {
            color.add("white");
        }

        return DFS(startIndex, map, color);
    }



    private static Set<Integer> DFS(int startVertex, Map<Integer, Set<Integer>> map, List<String> color) {

        Set<Integer> linked = new LinkedHashSet<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {  // Пока стек не пуст:
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();


            if (color.get(v).equals("white")) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color.set(v, "gray");
                linked.add(v);
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины,
                // вместо вызова рекурсии
                for (Integer w : outgoingEdges(map, v)) {
                    // Для каждого исходящего ребра (v, w):
                    if (color.get(w).equals("white")) {
                        stack.push(w);
                    }
                }
            } else if (color.get(v).equals("gray")) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                color.set(v, "black");

            }
        }
        return linked;
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
            Set<Integer> set2 = new TreeSet<>(Comparator.reverseOrder());
            set2.add(startVertex);
            map.merge(endVertex, set2, (old, newV) -> {
                old.add(startVertex);
                return old;
            });
        }
        return map;
    }
}
