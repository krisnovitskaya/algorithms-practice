package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.H_flowers_field;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
/*
H. Поле с цветочками
 */
public class HFlowersField {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            String[] size = reader.readLine().split(" ");
            int I = Integer.parseInt(size[0]);
            int J = Integer.parseInt(size[1]);

            int[][] data = new int[I+1][J+1];

            for (int i = 0; i < I; i++) {
                String row = reader.readLine();
                for (int j = 1; j <= J; j++) {
                    data[i][j] = Integer.parseInt(String.valueOf(row.charAt(j-1)));
                }
            }

            int[][] dp = new int[I + 1][J + 1];
            for (int i = 0; i < I; i++) {
                Arrays.fill(dp[i], 0);
            }

            //counting
            for (int i = I -1; i >= 0; i--) {
                for (int j = 1; j <= J ; j++) {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + data[i][j];
                }
            }

            System.out.println(dp[0][J]);

        }
    }
}
