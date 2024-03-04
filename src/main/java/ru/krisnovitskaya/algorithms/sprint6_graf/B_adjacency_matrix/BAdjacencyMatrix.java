package ru.krisnovitskaya.algorithms.sprint6_graf.B_adjacency_matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
B. Перевести список ребер в матрицу смежности
 */
public class BAdjacencyMatrix {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            int[][] adjacencyMatrix = new int[vertexCount][vertexCount];
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                Arrays.fill(adjacencyMatrix[i], 0);
            }

            for (int i = 0; i < edgeCount ; i++) {
                String[] s = reader.readLine().split(" ", 2);
                adjacencyMatrix[Integer.parseInt(s[0]) - 1][Integer.parseInt(s[1]) - 1] = 1;
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                    sb.append(adjacencyMatrix[i][j]).append(" ");
                }
                sb.append("\n");
            }

            writer.write(sb.toString());
        }
    }
}
/*

5 3
1 3
2 3
5 2

0 0 1 0 0
0 0 1 0 0
0 0 0 0 0
0 0 0 0 0
0 1 0 0 0
 */