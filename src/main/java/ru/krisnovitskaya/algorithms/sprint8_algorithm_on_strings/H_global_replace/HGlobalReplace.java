package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.H_global_replace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
H. Глобальная замена
 */
public class HGlobalReplace {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
             String text = reader.readLine();
             String pattern = reader.readLine();
             String replacing = reader.readLine();
             List<Integer> replacingIndexes = search(pattern, text);

             String result = replace(text, pattern, replacingIndexes,replacing);
            System.out.println(result);

        }
    }

    private static String replace(String text, String pattern, List<Integer> replacingIndexes, String replacing) {
        StringBuilder sb = new StringBuilder();
        int appendedIndex = 0;
        for (int i = 0; i < replacingIndexes.size(); i++) {
            sb.append(text.substring(appendedIndex, replacingIndexes.get(i)));
            sb.append(replacing);
            appendedIndex = replacingIndexes.get(i) + pattern.length();
        }
        if (appendedIndex < text.length()) {
            sb.append(text.substring(appendedIndex, text.length()));
        }
        return sb.toString();
    }

    private static List<Integer> search(String pattern, String text) {
        // Функция возвращает все позиции вхождения шаблона в тексте.
        List<Integer> result = new ArrayList<>();
        String s = pattern + "#" + text;
        int[] pi = new int[pattern.length()];  // Массив длины |p|.
        Arrays.fill(pi, 0);
        int piPrev = 0;
        for (int i = 1; i < s.length(); i++) {
            int k = piPrev;
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = pi[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            // Запоминаем только первые |p| значений π-функции.
            if (i < pattern.length()) {
                pi[i] = k;
            }
            // Запоминаем последнее значение π-функции.
            piPrev = k;
            // Если значение π-функции равно длине шаблона, то вхождение найдено.
            if (k == pattern.length()) {
                // i - это позиция конца вхождения шаблона.
                // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
                //  - чтобы «переместиться» на начало найденного шаблона,
                //  - чтобы не учитывать добавленное "pattern#".
                result.add(i - 2 * pattern.length());
            }
        }
        return result;
    }
}
