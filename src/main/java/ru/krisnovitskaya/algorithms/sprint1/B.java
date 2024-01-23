package ru.krisnovitskaya.algorithms.sprint1;

import java.util.Scanner;

/*
B. Чётные и нечётные числа

Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt

Представьте себе онлайн-игру для поездки в метро:
 игрок нажимает на кнопку, и на экране появляются три случайных числа.
  Если все три числа оказываются одной чётности, игрок выигрывает.

Напишите программу, которая по трём числам определяет, выиграл игрок или нет.

Формат ввода
В первой строке записаны три случайных целых числа a, b и c. Числа не превосходят 109 по модулю.

Формат вывода
Выведите «WIN», если игрок выиграл, и «FAIL» в противном случае.

Пример 1
Ввод	Вывод
1 2 -3   FAIL
Пример 2
Ввод	Вывод
7 11 7   WIN
Пример 3
Ввод	Вывод
6 -2 0   WIN
 */
public class B {

    private static boolean checkParity(int a, int b, int c) {
        return a % 2 == 0 ? b % 2 == 0 && c % 2 == 0 : b % 2 != 0 && c % 2 != 0;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if (checkParity(a, b, c)) {
                System.out.println("WIN");
            } else {
                System.out.println("FAIL");
            }
        }
    }
}
