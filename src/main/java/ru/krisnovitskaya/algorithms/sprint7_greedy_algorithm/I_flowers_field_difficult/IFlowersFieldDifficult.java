package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.I_flowers_field_difficult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
/*
* I. Сложное поле с цветочками*/
public class IFlowersFieldDifficult {
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

            dpCounting(I, J, data, dp);

            writer.println(dp[0][J]);

            String result = countPath(I, J, dp);

            writer.println(result);

        }
    }

    private static void dpCounting(int I, int J, int[][] data, int[][] dp) {
        //counting
        for (int i = I -1; i >= 0; i--) {
            for (int j = 1; j <= J; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + data[i][j];
            }
        }
    }

    private static String countPath(int I, int J, int[][] dp) {
        if(I ==1 && J == 1) {
            return "";
        }

        StringBuilder path = new StringBuilder();
        int i = 0;
        int j = J;


            while (true) {
                if (i == I - 1) {
                    path.append("R");
                    j--;
                } else if (j == 1) {
                    path.append('U');
                    i++;
                } else {
                    if (dp[i + 1][j] >= dp[i][j - 1]) {
                        path.append('U');
                        i++;
                    } else {
                        path.append("R");
                        j--;
                    }
                }
                if (i == I - 1 && j == 1) {
                    break;
                }
            }
        return path.reverse().toString();
    }
}
