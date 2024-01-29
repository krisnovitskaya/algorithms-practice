package ru.krisnovitskaya.algorithms.sprint2;

import java.util.Scanner;

/*
K. Рекурсивные числа Фибоначчи
*/
public class K {
    public static void main(String[] args) {
        try (Scanner reader = new Scanner(System.in)) {

            int traineeCount = reader.nextInt();
            int result = calcFibonacci(traineeCount);
            System.out.println(result);
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
