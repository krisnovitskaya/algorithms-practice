package ru.krisnovitskaya.algorithms.sprint2;

import java.io.*;
import java.util.Scanner;

/*
A. Мониторинг
 */
public class A {

    public static void main(String[] args) throws IOException {

        try (Scanner reader = new Scanner(new File("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            int rowCount = reader.nextInt();
            int colCount = reader.nextInt();

            int[][] toRotate = readMatrix(rowCount, colCount, reader);
            int[][] rotated = rotate(toRotate);
            printMatrix(rotated, writer);

        }
    }

    private static int[][] rotate(int[][] toRotate) {
        int[][] arr = new int[toRotate.length > 0 ? toRotate[0].length : 0][toRotate.length];
        for (int i = 0; i < toRotate.length; i++) {
            for (int j = 0; j < toRotate[i].length; j++) {
                arr[j][i] = toRotate[i][j];
            }
        }
        return arr;
    }

    private static void printMatrix(int[][] matrix, PrintWriter writer) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                writer.print(matrix[i][j]);
                writer.print(" ");
            }
            writer.println();
        }
    }

    private static int[][] readMatrix(int rowCount, int colCount, Scanner reader) {
        int [][] matrix = new int[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                matrix[i][j] = reader.nextInt();
            }
        }
        return matrix;
    }
}
