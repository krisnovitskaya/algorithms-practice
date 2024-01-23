package ru.krisnovitskaya.algorithms.sprint1;

/*
E. Самое длинное слово
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Чтобы подготовиться к семинару, Гоше надо прочитать статью по эффективному менеджменту. Так как Гоша хочет спланировать день заранее, ему необходимо оценить сложность статьи.

Он придумал такой метод оценки: берётся случайное предложение из текста и в нём ищется самое длинное слово. Его длина и будет условной сложностью статьи.

Помогите Гоше справиться с этой задачей.

Формат ввода
В первой строке дана длина текста L (1 ≤ L ≤ 105).

В следующей строке записан текст, состоящий из строчных латинских букв и пробелов. Слово —– последовательность букв, не разделённых пробелами. Пробелы могут стоять в самом начале строки и в самом её конце. Текст заканчивается переносом строки, этот символ не включается в число остальных L символов.

Формат вывода
В первой строке выведите самое длинное слово. Во второй строке выведите его длину. Если подходящих слов несколько, выведите то, которое встречается раньше.

Пример 1
Ввод
19
i love segment tree
Вывод
segment
7
Пример 2
Ввод
21
frog jumps from river
Вывод
jumps
5

 */


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int textLength = readInt(reader);
            List<String> words = readWords(reader);

            List<String> answer = evaluate(words);
            for (String s : answer) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    private static List<String> evaluate(List<String> words) {
        String word = words.get(0);
        int wordSize = word.length();

        for (int i = 1; i < words.size(); i++) {
            if(words.get(i).length() > wordSize){
                word = words.get(i);
                wordSize = word.length();
            }
        }

        List<String> answer = new ArrayList<>(2);
        answer.add(word);
        answer.add(String.valueOf(wordSize));
        return answer;
    }

    private static List<String> readWords(BufferedReader reader) throws IOException{
       return Arrays.stream(reader.readLine().trim().split(" ")).collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
