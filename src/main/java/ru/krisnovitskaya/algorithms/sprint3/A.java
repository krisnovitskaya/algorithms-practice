package ru.krisnovitskaya.algorithms.sprint3;
/*
A. Генератор скобок
*/

import java.util.Scanner;

public class A {
        public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)){
                int bracketCount = scanner.nextInt();
                genBrackets(bracketCount, 0, 0, "");
            }
        }

        static void genBrackets(int n, int countOpen, int countClose,  String curResult) {
            if(countOpen + countClose == n * 2){
                System.out.println(curResult);
                return;
            }
            if (countOpen < n) {
                genBrackets(n, countOpen + 1, countClose, curResult + "(");
            }
            if(countOpen > countClose) {
                genBrackets(n, countOpen, countClose +1, curResult + ")");
            }
        }
}
