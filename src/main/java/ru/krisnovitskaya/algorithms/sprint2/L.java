package ru.krisnovitskaya.algorithms.sprint2;

import java.util.Scanner;

/*
L. Фибоначчи по модулю
*/
public class L {
    public static void main(String[] args) {
        try (Scanner reader = new Scanner(System.in)) {
            //todo

        }
    }

    private static int calcFibonacci(int fibonacciIndex) {
        if (fibonacciIndex == 0 || fibonacciIndex == 1) {
            return 1;
        } else {
            return calcFibonacci(fibonacciIndex - 1) + calcFibonacci(fibonacciIndex - 2);
        }
    }
}
