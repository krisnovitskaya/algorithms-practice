package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.K_horoscope;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
K. Гороскопы
 */
public class KHoroscopes {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {

            //read data
            int firstLength = Integer.parseInt(reader.readLine());
            int[] first = new int[firstLength + 1];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= firstLength; i++) {
                first[i] = Integer.parseInt(tokenizer.nextToken());
            }

            int secondLength = Integer.parseInt(reader.readLine());
            int[] second = new int[secondLength + 1];
            StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= secondLength; i++) {
                second[i] = Integer.parseInt(tokenizer2.nextToken());
            }

            //prepare
            int[][] dp = new int[firstLength + 1][secondLength + 1];
            Arrays.fill(dp[0],0);
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 0;
            }


            // counting
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[i].length; j++) {
                    if(first[i] != second[j]){
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }
            }

            Stack<Integer> stackFirst = new Stack<>();
            Stack<Integer> stackSecond = new Stack<>();

            int i = firstLength;
            int j = secondLength;

            while (dp[i][j] > 0) {
                if(first[i] == second[j]){
                    stackFirst.add(i);
                    stackSecond.add(j);
                    i--;
                    j--;
                } else {
                    if(dp[i][j] == dp[i - 1][j]){
                        i--;
                    }else { //dp[i][j] == dp[i][j - 1]
                        j--;
                    }
                }
            }


            StringBuilder sb = new StringBuilder();
            sb.append(dp[firstLength][secondLength]).append("\n");

            while (!stackFirst.isEmpty()){
                sb.append(stackFirst.pop()).append(" ");
            }
            sb.append("\n");

            while (!stackSecond.isEmpty()){
                sb.append(stackSecond.pop()).append(" ");
            }
            sb.append("\n");

            writer.print(sb);
        }
    }
}
