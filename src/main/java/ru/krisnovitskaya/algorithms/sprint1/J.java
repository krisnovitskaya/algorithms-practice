package ru.krisnovitskaya.algorithms.sprint1;
/*
J. Факторизация
Язык	Ограничение времени	Ограничение памяти	Ввод	Вывод
Все языки	0.052 секунды	64Mb	стандартный ввод или input.txt	стандартный вывод или output.txt
Node.js 14.15.5	0.4 секунды	64Mb
Python 3.7.3	0.2 секунды	64Mb
OpenJDK Java 11	0.4 секунды	64Mb
C# (MS .NET 6.0 + ASP)	0.4 секунды	64Mb
Kotlin 1.8.0 (JRE 11)	0.4 секунды	64Mb
Python 3.11.4	0.2 секунды	64Mb
C# (MS .NET 5.0 + ASP)	0.4 секунды	64Mb
Основная теорема арифметики говорит: любое число раскладывается на произведение простых множителей единственным образом, с точностью до их перестановки. Например:

Число 8 можно представить как 2 × 2 × 2.
Число 50 –— как 2 × 5 × 5 (или 5 × 5 × 2, или 5 × 2 × 5). Три варианта отличаются лишь порядком следования множителей.
Разложение числа на простые множители называется факторизацией числа.

Напишите программу, которая производит факторизацию переданного числа.

Формат ввода
В единственной строке дано число n (2 ≤ n ≤ 109), которое нужно факторизовать.

Формат вывода
Выведите в порядке неубывания простые множители, на которые раскладывается число n.

Пример 1
Ввод	Вывод
8
2 2 2
Пример 2
Ввод	Вывод
13
13
Пример 3
Ввод	Вывод
100
2 2 5 5


 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class J {
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
        int currentPrime = 2;
        while (true) {
            if (value % currentPrime != 0) {
                currentPrime = nextPrime(currentPrime, input);
            } else if ((value = value/currentPrime) == 1) {
                result.add(currentPrime);
                break;
            }else {
                result.add(currentPrime);
            }
        }
        //code
        return result;
    }

    private static boolean isPrime(int input) {
        int i = 2;
        while (i * i <= input) {
            if (input % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static int nextPrime(int currentPrime, int value){
        for (int number = currentPrime + 1; number <= value; number++) {
            if(isPrime(number)){
                return number;
            }
        }
        return -1; //не достижимо
    }
}
