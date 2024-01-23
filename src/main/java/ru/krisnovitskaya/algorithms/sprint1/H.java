package ru.krisnovitskaya.algorithms.sprint1;


/*
H. Двоичная система
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Тимофей записал два числа в двоичной системе счисления и попросил Гошу вывести их сумму, также в двоичной системе. Встроенную в язык программирования возможность сложения двоичных чисел применять нельзя. Помогите Гоше решить задачу.

Решение должно работать за O(N), где N –— количество разрядов максимального числа на входе.

Формат ввода
Два числа в двоичной системе счисления, каждое на отдельной строке. Длина каждого числа не превосходит 10 000 символов.

Формат вывода
Одно число в двоичной системе счисления.

Пример 1
Ввод	Вывод
1010
1011
10101
Пример 2
Ввод	Вывод
1
1
10

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class H {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String first = scanner.next();
            String second = scanner.next();
            System.out.println(evaluate(first, second));
        }
    }

    private static String evaluate(String value, String value2) {
        char[] one;
        char[] two;
        if (value.length() == value2.length()) {
            one = value.toCharArray();
            two = value2.toCharArray();
        } else if (value.length() > value2.length()) {
            one = value.toCharArray();
            two = new char[one.length];
            Arrays.fill(two, '0');
            System.arraycopy(value2.toCharArray(), 0, two, two.length - value2.length(), value2.length());
        } else {
            one = value2.toCharArray();
            two = new char[one.length];
            Arrays.fill(two, '0');
            System.arraycopy(value.toCharArray(), 0, two, two.length - value.length(), value.length());
        }

        List<Character> result = new ArrayList<>();


        char temp = '0';
        char char000 = '0' + '0' + '0';
        char char100 = '1' + '0' + '0';
        char char110 = '1' + '1' + '0';
        char char111 = '1' + '1' + '1';

        char dump;
        for (int i = one.length - 1; i >= 0; i--) {
            dump = (char) (one[i] + two[i] + temp);

            if(dump == char000){
                result.add('0');
                temp = '0';
            }else if (dump == char100){
                result.add('1');
                temp = '0';
            }else if(dump == char110){
                result.add('0');
                temp = '1';
            }else { //char111
                result.add('1');
                temp = '1';
            }
        }
        if(temp == '1'){
            result.add('1');
        }

        return new StringBuilder(result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining())).reverse().toString();
    }
}
