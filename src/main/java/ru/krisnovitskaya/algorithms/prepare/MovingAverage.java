package ru.krisnovitskaya.algorithms.prepare;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class MovingAverage {
    private static List<Double> movingAverage(int n, List<Integer> arr, int windowSize) {
        // ваше решение
        List<Double> result = new ArrayList<>(arr.size() - windowSize + 1);
        Double sum = 0.0;
        for (int i = 0; i < windowSize; i++) {
            sum += arr.get(i);
        }
        result.add(sum/windowSize);
        for (int i = 0; i < arr.size() - windowSize; i++) {
            sum -= arr.get(i);
            sum += arr.get(i + windowSize);
            result.add(sum/windowSize);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int windowSize = readInt(reader);
            List<Double> result = movingAverage(n, arr, windowSize);
            for (double elem : result) {
                writer.write(elem + " ");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
