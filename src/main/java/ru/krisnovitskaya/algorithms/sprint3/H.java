package ru.krisnovitskaya.algorithms.sprint3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/*
H. Большое число
 */
public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            int numberCounts = Integer.parseInt(reader.readLine());
            String[] numbers = reader.readLine().split(" ", numberCounts);
            ResultContainer result = new ResultContainer();
            permutations(numbers, 0, result);
            writer.println(result.result);
        }
    }

    static void permutations(String[] numbers, int pos, ResultContainer result) {
        if (pos >= numbers.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numbers.length; i++) {
                sb.append(numbers[i]);
            }
            result.updateIfGreater(new BigInteger(sb.toString()));
        } else {
            for (int i = pos; i < numbers.length; ++i) {
                swap(numbers, i, pos);
                permutations(numbers, pos + 1, result);
                swap(numbers, i, pos);
            }
        }
    }

    private static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    static class ResultContainer {
        private BigInteger result;

        public void updateIfGreater(BigInteger bigInteger){
            if(result == null){
                result = bigInteger;
            }else {
                if(bigInteger.compareTo(result) > 0){
                    result = bigInteger;
                }
            }
        }
    }

}
//    H. Большое число
//    Язык	Ограничение времени	Ограничение памяти	Ввод	Вывод
//        Все языки	0.055 секунд	64Mb	стандартный ввод или input.txt	стандартный вывод или output.txt
//        Node.js 14.15.5	0.5 секунд	64Mb
//        Python 3.7.3	0.5 секунд	64Mb
//        OpenJDK Java 11	0.5 секунд	64Mb
//        C# (MS .NET 6.0 + ASP)	0.5 секунд	64Mb
//        Kotlin 1.8.0 (JRE 11)	0.5 секунд	64Mb
//        Python 3.11.4	0.5 секунд	64Mb
//        C# (MS .NET 5.0 + ASP)	0.5 секунд	64Mb
//        Вечером ребята решили поиграть в игру «Большое число».
//        Даны числа. Нужно определить, какое самое большое число можно из них составить.
//
//        Формат ввода
//        В первой строке записано n — количество чисел. Оно не превосходит 100.
//        Во второй строке через пробел записаны n неотрицательных чисел, каждое из которых не превосходит 1000.
//
//        Формат вывода
//        Нужно вывести самое большое число, которое можно составить из данных чисел.
//
//        Пример 1
//        Ввод	Вывод
//        3
//        15 56 2
//        56215
//        Пример 2
//        Ввод	Вывод
//        3
//        1 783 2
//        78321
//        Пример 3
//        Ввод	Вывод
//        5
//        2 4 5 2 10
//        542210
