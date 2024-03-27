package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.L_leprechaun_gold;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LLeprechaunGold {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            String[] s = reader.readLine().split(" ", 2);
            int bullionCount = Integer.parseInt(s[0]);
            int bagSize = Integer.parseInt(s[1]);

            //read data
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Bullion[] bullions = new Bullion[bullionCount];
            for (int i = 0; i < bullions.length; i++) {
                bullions[i] = new Bullion(Integer.parseInt(tokenizer.nextToken()));
            }

            //prepare dp
            int[][] dp = new int[bullionCount][bagSize + 1];
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], 0);
            }

            //fill zero case
            if(bagSize >= bullions[0].getWeight()){
                for (int j = bullions[0].getWeight(); j <= bagSize; j++) {
                    dp[0][j] = bullions[0].getWeight();
                }
            }


            //counting
            for (int i = 1; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                        dp[i][j] = Math.max(dp[i - 1][j], j - bullions[i].getWeight() <= 0 ? 0: bullions[i].getCost() + dp[i - 1][j - bullions[i].getWeight()]);
                        if(j >= bullions[i].getWeight()){
                            dp[i][j] = Math.max(dp[i][j], bullions[i].getCost());
                        }
                }
            }
            //print result
            writer.println(dp[bullionCount -1][bagSize]);
        }
    }

    static class Bullion {
        private int weight;

        public int getWeight() {
            return weight;
        }

        public int getCost() {
            return cost;
        }

        private int cost;

        public Bullion(int weight) {
            this.weight = weight;
            this.cost = weight;
        }
    }
}
