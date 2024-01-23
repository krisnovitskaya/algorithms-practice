package ru.krisnovitskaya.algorithms.sprint1.finals;
// https://contest.yandex.ru/contest/22450/run-report/105414764/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 A. Ближайший ноль
 */
public class AFinal4 {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {

            int areaCount = readInt(reader);

            List<Integer> result = countMinZeroLength(readAreas(reader, areaCount));

            writeResult(result);
        }
    }

    private static List<Integer> countMinZeroLength(List<Integer> areas) {
        if (areas.size() == 1) {
            return List.of(0);
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
        return areas;
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

    private static void writeResult(List<Integer> result) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int i = 0; i < result.size(); i++) {
                writer.write(String.valueOf(result.get(i)));
                if(i != result.size() - 1) {
                    writer.write(" ");
                }
            }
            writer.flush();
        }
    }
}
