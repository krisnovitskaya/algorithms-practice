package ru.krisnovitskaya.algorithms.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
L. Фибоначчи по модулю
*/
public class L {
    public static void main(String[] args) throws IOException {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int fibonacciIndex = Integer.parseInt(tokenizer.nextToken()); //0 ..10^6
            int countLastNumber = Integer.parseInt(tokenizer.nextToken());//1..8
            int modValue = (int)Math.pow(10, countLastNumber);


            int result = calcFibonacciMod(fibonacciIndex, modValue);
            System.out.println(result);
        }
    }

    private static int calcFibonacciMod(int fibonacciIndex, int modValue) {
        if (fibonacciIndex == 0 || fibonacciIndex == 1) {
            return 1;
        } else {
            int n2 = 1;
            int n1 = 1;
            int cur = 1;
            for (int i = 2; i <= fibonacciIndex; i++) {
                cur = (n1 + n2) % modValue;
                n2 = n1;
                n1 = cur;
            }
            return cur;
        }
    }
}
