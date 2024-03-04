package ru.krisnovitskaya.algorithms.sprint6_graf.A_build_adjancency_list;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
A. Построить список смежности
 */
public class ABuildAdjacencyList {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);


            Map<Integer, Queue<Integer>> map = new HashMap<>();
            for (int i = 1; i <= edgeCount; i++) {
                String[] s = reader.readLine().split(" ", 2);
                int startVertex = Integer.parseInt(s[0]);
                int endVertex = Integer.parseInt(s[1]);
                map.merge(startVertex, new PriorityQueue<>(List.of(endVertex)), (old, newV) -> {
                    old.add(endVertex);
                    return old;
                });
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= vertexCount; i++) {
                Queue<Integer> queue = map.get(i);
                if(queue == null){
                    sb.append(0).append("\n");
                }else {
                    sb.append(queue.size()).append(" ");
                    while (queue.size() > 0) {
                        sb.append(queue.poll()).append(" ");
                    }
                    sb.append("\n");
                }
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

1 3
1 3
0
0
1 2
 */
