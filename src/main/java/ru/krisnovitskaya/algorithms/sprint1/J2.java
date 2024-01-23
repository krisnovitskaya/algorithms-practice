package ru.krisnovitskaya.algorithms.sprint1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class J2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int value = scanner.nextInt(); //2..10^9
            List<Integer> res = evaluate(value);
            System.out.println(res.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static List<Integer> evaluate(int input) {
        List<Integer> result = new ArrayList<>();
        if(input == 2){
            result.add(2);
            return result;
        }

        int value = input;
        List<Integer> primes = getLeastPrimesLinear(input);
        int primeIndex = 0;
        int currentPrime = primes.get(0);
        return result;
//        while (true) {
//            if (value % currentPrime != 0) {
//                currentPrime = primes.get(++primeIndex);
//            } else if ((value = value/currentPrime) == 1) {
//                result.add(currentPrime);
//                break;
//            }else {
//                result.add(currentPrime);
//            }
//        }
//        //code
//        return result;
    }

    private static List<Integer> getLeastPrimesLinear(int n) {
        int[] lp = new int[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (lp[i] == 0) {
                lp[i] = i;
                primes.add(i);
            }
            for (int j = 0; j < primes.size(); j++) {
                int p = primes.get(j);
                int x = p * i;
                if (p > lp[i] || x > n) {
                    break;
                }
                lp[x] = p;
            }
        }
        return primes;
    }
}
