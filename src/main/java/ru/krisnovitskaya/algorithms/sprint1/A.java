package ru.krisnovitskaya.algorithms.sprint1;

/*
A. Значения функции
Вася делает тест по математике: вычисляет значение функций в различных точках.
 Стоит отличная погода, и друзья зовут Васю гулять.
  Но мальчик решил сначала закончить тест и только после этого идти к друзьям.
   К сожалению, Вася пока не умеет программировать. Зато вы умеете.
    Помогите Васе написать код функции, вычисляющей y = ax2 + bx + c.
     Напишите программу, которая будет по коэффициентам a, b, c и числу x выводить значение функции в точке x.


Формат ввода
На вход через пробел подаются целые числа a, x, b, c. В конце ввода находится перенос строки.

Формат вывода
Выведите одно число — значение функции в точке x.

-8 -5 -2 7      -> -183
8 2 9 -10       -> 40
*/


import java.util.Scanner;

public class A {

    private static int evaluateFunction(int a, int b, int c, int x) {
        // Ваше решение
        return a * x * x + b * x + c;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int a = scanner.nextInt();
            int x = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            System.out.println(evaluateFunction(a, b, c, x));
        }
    }
}
