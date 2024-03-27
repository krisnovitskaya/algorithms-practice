package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.F_jumping_on_stairs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FJumpStairs {
    private static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            String[] s = reader.readLine().split(" ");
            int stepCount = Integer.parseInt(s[0]);
            int jumpLength = Integer.parseInt(s[1]);


            long[] stairs = new long[stepCount + 1];
            stairs[0] = 0;
            stairs[1] = 1;

            for(int i = 2; i < stairs.length; i++) {
                if (jumpLength >= i) {
                    for( int j = 1; j < i; j++) {
                        stairs[i] += stairs[j];
                    }
                } else {
                    for (int j = i - jumpLength; j < i; j++) {
                        stairs[i] += stairs[j];
                    }
                }
                stairs[i] = stairs[i] % MOD;
            }

            System.out.println(stairs[stepCount]);
        }
    }
}
