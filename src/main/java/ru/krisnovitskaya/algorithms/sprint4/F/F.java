package ru.krisnovitskaya.algorithms.sprint4.F;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
F. Анаграммная группировка
 */
public class F {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            Map<Map<Character, Integer>, List<Integer>> result = process(reader);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Map<Character, Integer>, List<Integer>> entry : result.entrySet()) {
                List<Integer> value = entry.getValue();
                for (Integer integer : value) {
                    sb.append(integer).append(" ");
                }
                sb.append("\n");
            }
            writer.print(sb);
        }
    }

    private static Map<Map<Character, Integer>, List<Integer>> process(BufferedReader reader) throws IOException {
        Map<Map<Character, Integer>, List<Integer>> result = new LinkedHashMap<>();
        int count = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            String word = tokenizer.nextToken();
            Map<Character, Integer> aMap = new HashMap<>();
            for (int k = 0; k < word.length(); k++) {
                aMap.merge(word.charAt(k), 1, Integer::sum);
            }

            int finalI = i;
            result.merge(aMap, new ArrayList<>(List.of(finalI)), (old, newV) -> {
                old.add(finalI);
                return old;
            });
        }
        return result;
    }
}
