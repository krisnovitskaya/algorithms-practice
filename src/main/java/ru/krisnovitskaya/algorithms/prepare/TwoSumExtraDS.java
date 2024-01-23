package ru.krisnovitskaya.algorithms.prepare;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TwoSumExtraDS {
    // Если ответ существует, верните список из двух элементов
    // Если нет - то верните пустой список
    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        // Ваше решение

        List<Integer> result = new ArrayList<>(2);
        Set<Integer> storage = new HashSet<>();

        Integer calc;
        for (Integer number : arr) {
            calc = targetSum - number;
            if(storage.contains(calc)){
                result.add(number);
                result.add(calc);
                break;
            }else {
                storage.add(number);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int targetSum = readInt(reader);
            List<Integer> result = twoSum(arr, targetSum);
            if (result.isEmpty()) {
                writer.write("None");
            } else {
                writer.write(result.get(0) + " " + result.get(1));
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
