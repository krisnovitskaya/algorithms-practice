package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.G_shift_search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/*
G. Поиск со сдвигом
 */
public class GShiftSearch {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            //reading input
            int sequenceLength = Integer.parseInt(reader.readLine());
            int[] data = new int[sequenceLength];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < sequenceLength; i++) {
                data[i] = Integer.parseInt(tokenizer.nextToken());
            }

            int patternLength = Integer.parseInt(reader.readLine());
            int[] pattern = new int[patternLength];
            StringTokenizer tokenizerPattern = new StringTokenizer(reader.readLine());
            for (int i = 0; i < patternLength; i++) {
                pattern[i] = Integer.parseInt(tokenizerPattern.nextToken());
            }


            //counting
            Deque<Integer> result = count(data, pattern);

            String collect = result.stream().map(i -> String.valueOf(i + 1)).collect(Collectors.joining(" "));
            writer.println(collect);

        }
    }

    private static Deque<Integer> count(int[] data, int[] pattern) {
        Deque<Integer> result = new LinkedList<>();
        for (int i = 0; i < data.length - pattern.length + 1;i++) {
            int diff = data[i] - pattern[0];
            result.add(i);
            for (int j = 1; j < pattern.length; j++) {
                if(data[i + j] - pattern[j] != diff){
                    result.removeLast();
                    break;
                }
            }
        }
        return result;
    }
}
