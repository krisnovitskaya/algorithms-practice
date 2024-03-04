package ru.krisnovitskaya.algorithms.sprint6_graf.E_connectivity_components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class EConnectivityComponents {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Map<Integer, List<Integer>> map = fillMapGraf(reader, vertexCount, edgeCount);

            Map<Integer, StringBuilder> result = connectivityGroup(vertexCount, map);
            StringBuilder sb = new StringBuilder();
            sb.append(result.size()).append("\n");
            for (var value : result.values()) {
                sb.append(value).append("\n");
            }
            writer.write(sb.toString());
        }
    }

    private static Map<Integer, StringBuilder> connectivityGroup(int vertexCount, Map<Integer, List<Integer>> map) {
        Integer[] color = new Integer[vertexCount + 1];

        int curColor = 1;
        for (int i = 1; i <= vertexCount; i++){
            if(color[i] == null){
                DFS(i, map, color, curColor++);
            }
        }

        Map<Integer, StringBuilder> resultMap = new LinkedHashMap<>();
        for (int i = 1; i <= vertexCount; i++) {
            int finalI = i;
            resultMap.merge(color[i], new StringBuilder(i + " "), (oldV, newV) -> {
                oldV.append(finalI).append(" ");
                return oldV;
            });
        }
        return resultMap;
    }



    private static void DFS(int startVertex, Map<Integer, List<Integer>> map, Integer[] color, int curColor) {

        color[startVertex] = 0;
        List<Integer> outgoingEdges = outgoingEdges(map, startVertex);
        for (int w : outgoingEdges) {
            if (color[w] == null) {
                DFS(w, map, color, curColor);
            }
        }
        color[startVertex] = curColor;
    }

    private static List<Integer> outgoingEdges(Map<Integer, List<Integer>> map, int index){
        return Optional.ofNullable(map.get(index)).orElse(List.of());
    }



    private static Map<Integer, List<Integer>> fillMapGraf(BufferedReader reader, int vertexCount, int edgeCount) throws IOException {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= edgeCount; i++) {
            String[] s = reader.readLine().split(" ", 2);
            int startVertex = Integer.parseInt(s[0]);
            int endVertex = Integer.parseInt(s[1]);
            map.merge(startVertex, new ArrayList<>(List.of(endVertex)), (old, newV) -> {
                old.add(endVertex);
                return old;
            });
            map.merge(endVertex, new ArrayList<>(List.of(startVertex)), (old, newV) -> {
                old.add(startVertex);
                return old;
            });
        }
        return map;
    }
}