package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.L_count_prefix_function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
L. Подсчёт префикс-функции
 */
public class LCountPrefixFunction {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            String s = reader.readLine();
//            int[] result = prefixFunction(s);
            int[] result = prefixFunctionNaive(s);

            writer.println(prepareResultToPrint(result));
        }
    }

    private static String prepareResultToPrint(int[] result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]).append(" ");
        }
        return sb.toString();
    }

    public static int[] prefixFunction(String s) {
        // Функция возвращает массив длины |s|
        int n = s.length();
        int[] pi = new int[n];
        pi[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = pi[i - 1];
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = pi[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            pi[i] = k;
        }
        return pi;
    }

    public static int[] prefixFunctionNaive(String s) {
        int n = s.length();
        int[] pi = new int[n];

        for (int i = 1; i <= n; ++i) {
            // i — длина подстроки-префикса.
            String substring = s.substring(0, i);
            // Проверяем, перекрывается ли подстрока substring с собой по k символам.
            for (int k = i - 1; k > 0; --k) {
                // Для этого сравним префикс и суффикс соответствующих длин.
                String prefix = substring.substring(0, k);
                String suffix = substring.substring(i - k, i);
                if (prefix.equals(suffix)) {
                    pi[i - 1] = k; // Записываем значение π-функции.
                    break; // Дальше не проверяем — k идет в порядке уменьшения.
                }
            }
        }
            return pi;
    }
}
