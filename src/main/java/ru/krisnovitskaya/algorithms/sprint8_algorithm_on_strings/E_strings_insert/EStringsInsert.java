package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.E_strings_insert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/*
E. Вставка строк
 */

public class EStringsInsert {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {


            String origin = reader.readLine();
            int stringCount = Integer.parseInt(reader.readLine());

            Map<Integer, String> map = new TreeMap<>();
            for (int i = 0; i < stringCount; i++) {
                String[] s = reader.readLine().split(" ");
                map.put(Integer.parseInt(s[1]), s[0]);
            }

            StringBuilder sb = new StringBuilder();
            int appendedIndex = 0;
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                sb.append(origin.substring(appendedIndex, entry.getKey()));
                sb.append(entry.getValue());
                appendedIndex = entry.getKey();
            }
            if (appendedIndex < origin.length()) {
                sb.append(origin.substring(appendedIndex, origin.length()));
            }
            writer.println(sb);

        }
    }
}
