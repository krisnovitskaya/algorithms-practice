package ru.krisnovitskaya.algorithms.sprint3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

/*
H. Большое число
 */
public class H2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String first, String second) {
                    String a = first + second;
                    String b = second + first;
                    return -a.compareTo(b);
                }
            };

            int numberCounts = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ", numberCounts);
            Arrays.sort(strings, comparator);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
            }
            writer.println(sb);
        }
    }
}
