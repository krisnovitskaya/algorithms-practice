package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.K_compare_two_string;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
K. Сравнить две строки
 */
public class KCompareTwoString {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            String str1 = reader.readLine();
            String str2 = reader.readLine();

            int result = compare(filterString(str1), filterString(str2));

            writer.println(result);
        }
    }

    private static int compare(String s1, String s2) {
        int result = s1.compareTo(s2);
        if(result > 0){
            return 1;
        } else if (result < 0) {
            return -1;
        }else {
            return result;
        }
    }

    private static String filterString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) % 2 == 0){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
