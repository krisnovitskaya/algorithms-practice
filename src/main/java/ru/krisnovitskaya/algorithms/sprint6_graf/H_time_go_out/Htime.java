package ru.krisnovitskaya.algorithms.sprint6_graf.H_time_go_out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Htime {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ){
            String[] strings = reader.readLine().split(" ", 2);
            int vertexCount = Integer.parseInt(strings[0]);
            int edgeCount = Integer.parseInt(strings[1]);

            Map<Integer, Set<Integer>> map = fillMap(reader, vertexCount, edgeCount);

            List<TimeColor> result = mainDFSTime(1, vertexCount, map);

            writer.write((result.stream().skip(1L).map(ct -> ct.getTimeIn() + " " + ct.getTimeOut()).collect(Collectors.joining("\n"))));
//            System.out.println((result.stream().skip(1L).map(ct -> ct.getTimeIn() + " " + ct.getTimeOut()).collect(Collectors.joining("\n"))));

        }
    }

    private static List<TimeColor> mainDFSTime(int startIndex, int vertexCount, Map<Integer, Set<Integer>> map) {
        List<TimeColor> ct = new ArrayList<>();
        ct.add(null);
        for (int i = 1; i <= vertexCount; i++) {
            ct.add(new TimeColor());
        }

        return DFSTiming(startIndex, map, ct);
    }



    private static List<TimeColor>  DFSTiming(int startVertex, Map<Integer, Set<Integer>> map, List<TimeColor> color) {

        int time = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {  // Пока стек не пуст:
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();


            if (color.get(v).getColor().equals("white")) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color.get(v).setColor("gray");
                stack.push(v);
                color.get(v).setTimeIn(time++);

                // Теперь добавляем в стек все непосещённые соседние вершины,
                // вместо вызова рекурсии
                for (Integer w : outgoingEdges(map, v)) {
                    // Для каждого исходящего ребра (v, w):
                    if (color.get(w).getColor().equals("white")) {
                        stack.push(w);
                    }
                }
            } else if (color.get(v).getColor().equals("gray")) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                color.get(v).setColor("black");
                color.get(v).setTimeOut(time++);

            }
        }
        return color;
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

    static class TimeColor {
        private String color;
        private int timeIn;
        private int timeOut;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getTimeIn() {
            return timeIn;
        }

        public void setTimeIn(int timeIn) {
            this.timeIn = timeIn;
        }

        public int getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(int timeOut) {
            this.timeOut = timeOut;
        }

        public TimeColor() {
            this.color = "white";
            this.timeIn = -1;
            this.timeOut = -1;
        }
    }
}
