package ru.krisnovitskaya.algorithms.sprint1;


/*
L. Лишняя буква
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Васе очень нравятся задачи про строки, поэтому он придумал свою. Есть 2 строки s и t, состоящие только из строчных букв. Строка t получена перемешиванием букв строки s и добавлением 1 буквы в случайную позицию. Нужно найти добавленную букву.

Формат ввода
На вход подаются строки s и t, разделённые переносом строки. Длины строк не превосходят 1000 символов. Строки не бывают пустыми.

Формат вывода
Выведите лишнюю букву.

Пример 1
Ввод	Вывод
abcd
abcde
e
Пример 2
Ввод	Вывод
go
ogg
g
Пример 3
Ввод	Вывод
xtkpx
xkctpx
c

 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            Map<String, Integer> first = readAndCountSymbols(reader.readLine());
            Map<String, Integer> second = readAndCountSymbols(reader.readLine());
            String result = evaluate(first, second);
            writer.write(result);
        }
    }

    private static Map<String, Integer> readAndCountSymbols(String line) {
        Map<String, Integer> map = new HashMap<>();
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.merge(String.valueOf(chars[i]), 1, Integer::sum);
        }
        return map;
    }

    private static String evaluate(Map<String, Integer> first, Map<String, Integer> second) {
        if (second.size() > first.size()) {
            for (String s : second.keySet()) {
                if (first.get(s) == null) {
                    return s;
                }
            }
        }
        String result = null;
        for (String key : first.keySet()) {
            if (first.get(key) != second.get(key)) {
                result = key;
                break;
            }
        }
        return result;
    }
}
