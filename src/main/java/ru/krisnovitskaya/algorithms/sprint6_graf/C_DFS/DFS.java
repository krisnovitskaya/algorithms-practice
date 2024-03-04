package ru.krisnovitskaya.algorithms.sprint6_graf.C_DFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class DFS {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            int[][] matrix = fillMatrix(reader, vertexCount, edgeCount);

            int startIndex = Integer.parseInt(reader.readLine());
            Set<Integer> dfs = mainDFS(startIndex - 1, matrix);

//            writer.write(dfs.stream().map(integer -> integer + 1).map(integer -> integer.toString()).collect(Collectors.joining(" ")));
            System.out.println((dfs.stream().map(integer -> integer + 1).map(integer -> integer.toString()).collect(Collectors.joining(" "))));

        }
    }

    private static Set<Integer> mainDFS(int startIndex, int[][] matrix) {
        List<String> color = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            color.add("white");
        }

        return DFS(startIndex, matrix, color);
    }



    private static Set<Integer> DFS(int startVertex, int[][] matrix, List<String> color) {

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
                for (Integer w : outgoingEdges(matrix, v)) {
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

    private static List<Integer> outgoingEdges(int[][] matrix, int index){
        List<Integer> edges = new ArrayList<>();
        for (int i = matrix.length -1; i >= 0 ; i--) {
            if(matrix[index][i] == 1){
                edges.add(i);
            }
        }
        return edges;
    }

    private static int[][] fillMatrix(BufferedReader reader, int vertexCount, int edgeCount) throws IOException {
        int[][] adjacencyMatrix = new int[vertexCount][vertexCount];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            Arrays.fill(adjacencyMatrix[i], 0);
        }
        //неориентированный матрица зеркальна
        for (int i = 0; i < edgeCount; i++) {
            String[] s = reader.readLine().split(" ", 2);
            adjacencyMatrix[Integer.parseInt(s[0]) - 1][Integer.parseInt(s[1]) - 1] = 1;
            adjacencyMatrix[Integer.parseInt(s[1]) - 1][Integer.parseInt(s[0]) - 1] = 1;
        }
        return adjacencyMatrix;
    }
}
