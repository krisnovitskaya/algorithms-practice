package ru.krisnovitskaya.algorithms.sprint1;

/*
K. Списочная форма
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Вася просил Аллу помочь решить задачу. На этот раз по информатике.

Для неотрицательного целого числа X списочная форма –— это массив его цифр слева направо. К примеру, для 1231 списочная форма будет [1,2,3,1]. На вход подается количество цифр числа Х, списочная форма неотрицательного числа Х и неотрицательное число K. Число К не превосходят 10000. Длина числа Х не превосходит 1000.

Нужно вернуть списочную форму числа X + K.

Не используйте встроенные средства языка для сложения длинных чисел.

Формат ввода
В первой строке — длина списочной формы числа X. На следующей строке — сама списочная форма с цифрами записанными через пробел.

В последней строке записано число K, 0 ≤ K ≤ 10000.

Формат вывода
Выведите списочную форму числа X+K.

Пример 1
Ввод	Вывод
4
1 2 0 0
34
1 2 3 4
Пример 2
Ввод	Вывод
2
9 5
17
1 1 2

 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int bigNumberLength = readInt(reader);
            List<Integer> numbers = readArr(reader, bigNumberLength);
            List<Integer> addedValue = reader.readLine().chars().mapToObj(c -> (char) c).map(ch -> Character.getNumericValue(ch)).collect(Collectors.toList());
            String result = evaluate(numbers, addedValue);
            writer.write(result);
        }
    }

    private static String evaluate(List<Integer> numbers, List<Integer> addedValue) {

        List<Integer> one = numbers.size() > addedValue.size() ? numbers : addedValue;
        List<Integer> second = numbers.size() <= addedValue.size() ? numbers : addedValue;


        int oneIndx = one.size() - 1;
        int secondIndx = second.size() - 1;

        int temp = 0;
        int oneOrZero = 0;
        while (secondIndx >= 0) {
            temp = one.get(oneIndx) + second.get(secondIndx) + oneOrZero;
            one.set(oneIndx, temp % 10);
            oneOrZero = temp >= 10 ? 1 : 0;
            secondIndx--;
            oneIndx--;
        }

        while (oneIndx >= 0 && oneOrZero == 1) {
            temp = one.get(oneIndx) + oneOrZero;
            one.set(oneIndx, temp % 10);
            oneOrZero = temp >= 10 ? 1 : 0;
            oneIndx--;
        }

        String result = one.stream().map(String::valueOf).collect(Collectors.joining(" "));


        return oneOrZero == 1 ? "1 ".concat(result) : result;
    }

    private static List<Integer> readArr(BufferedReader reader, int length) throws IOException {
        List<Integer> arr = new ArrayList<>(length);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            arr.add(Integer.parseInt(tokenizer.nextToken()));
        }
        return arr;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
