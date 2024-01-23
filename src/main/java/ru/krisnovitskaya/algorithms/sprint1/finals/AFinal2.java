package ru.krisnovitskaya.algorithms.sprint1.finals;
// https://contest.yandex.ru/contest/22450/run-report/105400059/
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
 A. Ближайший ноль
 */
public class AFinal2 {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int areaCount = readInt(reader);
            List<Integer> areas = readAreas(reader, areaCount);
            String result = countMinZeroLength(areas);
            writer.write(result);
        }
    }

    private static String countMinZeroLength(List<Integer> areas) {
        if (areas.size() == 1) {
            return "0";
        }


        if (areas.get(areas.size() - 1) == 0) {
            areas.set(areas.size() - 1, 0);
        } else {
            areas.set(areas.size() - 1, areas.size());
        }

        for (int i = areas.size() - 2; i >= 0; i--) {
            if (areas.get(i) == 0) {
                areas.set(i, 0);
            } else {
                areas.set(i, areas.get(i + 1) + 1);
            }
        }

        for (int i = 1; i < areas.size(); i++) {
            if (areas.get(i) != 0) {
                areas.set(i, Math.min(areas.get(i), areas.get(i - 1) + 1));
            }
        }

        return areas.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private static List<Integer> readAreas(BufferedReader reader, int areaCount) throws IOException {
        List<Integer> areas = new ArrayList<>(areaCount);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < areaCount; i++) {
            areas.add(Integer.parseInt(tokenizer.nextToken()));
        }
        return areas;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
