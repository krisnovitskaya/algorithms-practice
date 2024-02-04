package ru.krisnovitskaya.algorithms.sprint3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * B. Комбинации
 * */
public class B {

    private static Map<String, String> map = Map.of(
            "2", "abc",
            "3", "def",
            "4", "ghi",
            "5", "jkl",
            "6", "mno",
            "7", "pqrs",
            "8", "tuv",
            "9", "wxyz"
    );

    private static List<String> calc(String input) {
        if (input.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        String firstSymbols = map.get(String.valueOf(input.charAt(0)));
        List<String> tails = input.length() == 1 ? new ArrayList<>() : calc(input.substring(1));

        if (!tails.isEmpty()) {
            for (int i = 0; i < firstSymbols.length(); i++) {
                for (String tail : tails) {
                    result.add(firstSymbols.charAt(i) + tail);
                }
            }
        } else {
            for (int i = 0; i < firstSymbols.length(); i++) {
                result.add(String.valueOf(firstSymbols.charAt(i)));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println(String.join(" ", calc(scanner.nextLine())));
        }
    }
}
