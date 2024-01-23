package ru.krisnovitskaya.algorithms.sprint1;



/*
G. Работа из дома
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Вася реализовал функцию, которая переводит целое число из десятичной системы в двоичную. Но, кажется, она получилась не очень оптимальной.

Попробуйте написать более эффективную программу.

Не используйте встроенные средства языка по переводу чисел в бинарное представление.

Формат ввода
На вход подаётся целое число в диапазоне от 0 до 10000.

Формат вывода
Выведите двоичное представление этого числа.

Пример 1
Ввод	Вывод
5
101
Пример 2
Ввод	Вывод
14
1110

 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class G {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int value = scanner.nextInt();
            System.out.println(evaluate(value));
        }
    }

    private static String evaluate(int value) {
        if(value == 0 ) {
            return "0";
        }

        List<Integer> list = new ArrayList<>();

        int divide = value;
        while (divide != 0){
            list.add(divide % 2);
            divide = divide / 2;
        }
        return new StringBuilder(list.stream()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.joining())).reverse().toString();
    }
}
