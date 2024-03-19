package ru.krisnovitskaya.algorithms.sprint6_graf.finals.B_railway;
// https://contest.yandex.ru/contest/25070/run-report/109555207/
/* -- ПРИНЦИП РАБОТЫ --
Алгоритм работы представляет из себя:
- создание и наполнение графа, где за направления ребер берутся разные типы B(туда) и R(обратно).
- поиск в глубину на нахождения циклов в графе.
Так как разные типы это ребра разного направления, (а по условию дорога может быть только от меньшего города к большему),
 то ребра могут образовывать циклы, только в случае наличия пути между разных точек разного типа. То есть движение внутрь
  графа при оптимальной схеме идет только вперед или только назад.
- вывод ответа: Если цикл есть, значит между точками A и B существует дороги двух типов и схема дорог не оптимальна
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - количество городов (вершин графа),
количество дорог (ребер графа) исходя из вводных данных будет n-1 + n-2 + n-3 .. +1 -> (N^2)/2
Создание графа и списка смежности (в задаче используется единая структура типа Map<Integer, List<Integer>>)
занимает O(N) + O((N^2)/2) -> O(N +N^2)
Поиск в глубину для нахождения циклов в худшем случае (когда циклов нет) требует прохода по всем вершинам и соответсвенно
 по всем ребрам поэтому сложность будет примерно O(N^2)

Общая сложность =  O(N +N^2) + O(N^2) -> O(N + N^2)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
Хранение графа O(N) - вершины + O(N^2) ребра
Массив цветов при поиске в глубину O(N).
Stack для хранения необработанных вершин в максимуме (в основном дороги типа B) хранит почти все необработанные дороги ->
 O(N^2)
Максимальная пространственная сложность  O(N) + O(N^2) + O(N) + O(N^2) -> O(2N+ 2*N^2) -> O(N + N^2)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
B.Железные дороги
 */
public class B {
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final char B = 'B';
    private static final char R = 'R';

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            Integer townCount = Integer.parseInt(reader.readLine());
            TownGraph townGraph = createAndFill(reader, townCount);
            String result = checkRoads(townGraph);
            writer.println(result);
        }
    }

    private static String checkRoads(TownGraph townGraph) {
        Color[] colors = new Color[townGraph.vertexCount() + 1];
        for (int i = 1; i <= townGraph.vertexCount(); i++) {
            if (colors[i] == null) {
                if (isCycled(townGraph, i, colors)) {
                    return NO;
                }
            }
        }
        return YES;
    }

    private static boolean isCycled(TownGraph townGraph, Integer startVertex, Color[] colors) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Integer curVertex = stack.pop();
            if (colors[curVertex] == null) {
                colors[curVertex] = Color.GRAY;
                stack.push(curVertex);

                for (Integer linkVertex : townGraph.outgoingEdges(curVertex)) {
                    if (colors[linkVertex] == null) {
                        stack.push(linkVertex);
                    } else if (Color.GRAY == colors[linkVertex]) {
                        return true;
                    }
                }
            } else if (Color.GRAY == colors[curVertex]) {
                colors[curVertex] = Color.BLACK;
            }
        }
        return false;
    }

    private static TownGraph createAndFill(BufferedReader reader, Integer townCount) throws IOException {
        TownGraph townGraph = new TownGraph(townCount);
        for (int i = 1; i < townCount; i++) {
            String line = reader.readLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == B) {
                    townGraph.addEdge(i, i + j + 1);
                } else if (line.charAt(j) == R) {
                    townGraph.addEdge(i + j + 1, i);
                }
            }
        }
        return townGraph;
    }

    static class TownGraph {
        private Map<Integer, List<Integer>> edgeMap;

        public TownGraph(Integer townCount) {
            edgeMap = new HashMap<>();
            for (int i = 1; i <= townCount; i++) {
                edgeMap.put(i, new ArrayList<>());
            }
        }

        public List<Integer> outgoingEdges(Integer vertexNum) {
            return edgeMap.get(vertexNum);
        }

        public void addEdge(Integer vertexFrom, Integer vertexTo) {
            edgeMap.computeIfPresent(vertexFrom, (val, list) -> {
                list.add(vertexTo);
                return list;
            });
        }

        public int vertexCount() {
            return edgeMap.size();
        }
    }

    enum Color {
        GRAY, BLACK
    }
}
