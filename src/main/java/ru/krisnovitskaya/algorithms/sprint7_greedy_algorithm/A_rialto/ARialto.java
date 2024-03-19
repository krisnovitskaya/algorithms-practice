package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.A_rialto;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ARialto {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            Integer dayCount = Integer.parseInt(reader.readLine());
            String data = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(data);
            int[] prices = new int[dayCount];
            for (int i = 0; i < dayCount; i++) {
                prices[i] = Integer.parseInt(tokenizer.nextToken());
            }


            int result = countResult(prices);
            writer.println(result);

        }
    }

    private static int countResult(int[] prices) {
        int result = 0;
        int purchasePrice = 0;
        boolean needBuy = true;
        if (prices.length < 2) {
            return 0;
        } else if (prices.length == 2) {
            if (prices[0] < prices[1]) {
                return prices[1] - prices[0];
            }
        } else {
            for (int i = 0; i < prices.length; i++) {
                if (needBuy) {
                    if (i == prices.length - 1) {
                        break;
                    }
                    if (prices[i] < prices[i + 1]) {
                        purchasePrice = prices[i];
                        needBuy = false;
                    }
                } else {
                    if (i == prices.length - 1) {
                        result = result + (prices[i] - purchasePrice);
                        continue;
                    }
                    if (prices[i] >= prices[i + 1]) {
                        result = result + (prices[i] - purchasePrice);
                        needBuy = true;
                    }
                }
            }
        }
        return result;
    }
}
