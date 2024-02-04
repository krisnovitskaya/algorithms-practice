package ru.krisnovitskaya.algorithms.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
* J. Пузырёк
* */
public class J {
    public static void main(String[] args) throws IOException {
        try(BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in))) {
            int[] arr = readArray(scanner);
            sortAndPrintResult(arr);
        }
    }

    private static void sortAndPrintResult(int[] arr) {
        boolean printFlag = false;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;
                    printFlag = true;
                }
            }

            if(printFlag){
                printArr(arr);
            }else {
                if(i == 0){
                    printArr(arr);
                }
                break;
            }
            printFlag = false;
        }
    }

    private static void printArr(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]).append(" ");
        }
        System.out.println(stringBuilder);
    }

    private static int[] readArray(BufferedReader scanner) throws IOException {
        int arrLength = Integer.parseInt(scanner.readLine());
        StringTokenizer tokenizer = new StringTokenizer(scanner.readLine());
        int[] arr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }
}
