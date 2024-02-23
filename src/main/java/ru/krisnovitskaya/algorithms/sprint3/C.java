package ru.krisnovitskaya.algorithms.sprint3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
C. Подпоследовательность
 */
public class C {
    private static String TRUE = "True";
    private static String FALSE = "False";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            String search = reader.readLine();
            String data = reader.readLine();
            String result = process(search, data);
            writer.println(result);
        }
    }

    private static String process(String search, String data) {
        if (data.length() < search.length()) {
            return FALSE;
        }

        int k = 0;
        boolean searchFlag = false;
        for (int i = 0; i < search.length(); i++) {
            if (search.length() - i > data.length() - k) {
                return FALSE;
            }
            searchFlag = false;
            while (k < data.length()) {
                k++;
                if (search.charAt(i) == data.charAt(k - 1)) {
                    searchFlag = true;
                    break;
                }
            }
        }
        return searchFlag ? TRUE :FALSE;
    }
}
