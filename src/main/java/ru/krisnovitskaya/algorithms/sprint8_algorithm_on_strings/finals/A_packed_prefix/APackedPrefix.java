package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.finals.A_packed_prefix;
// https://contest.yandex.ru/contest/26133/run-report/113065764/

/*
 -- ПРИНЦИП РАБОТЫ --

Алгоритм вычисления максимального общего префикса состоит из 2х частей:
1) распаковки строки
2) вычисление максимального общего префикса с предыдущим результатом


Распаковка строки идет линейно-рекурсивно: проверяется каждый символ и если встречается цифра,
то согласно условию задачи далее следует ЗС в [].
Вычисляется индекс закрывающей скобки этой ЗС и вычисление подстроки передается рекурсивно в себя перед "умножением".

Вычисление общего префикса идет линейно, сраниваются посимвольно каждая новая строка и предыдущий префикс.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ
N - количество строк;
M - максимальная длина строки на входе; (~ длине распакованной строки)

Для распаковки строк необходимо пройтись полностью по строке, при этом, в худшем случае,
если высока вложенность ЗС ([[[[[]]]]]),
то алгоритм вычисления индекса закрывающей скобки будет так же часто вызываться
При n - минимальное количество символов запакованной строки ~ 4   (2[a])
Итерация по подстрокам M + M - n + M - 2n + M - 3n + .. + n -> M  -> (O(M)).
Временная сложность распаковки строк будет O(N * M)

Временная сложность высиления префикса в худшем случае , когда распакованные строки почти одинаковы будет O(N * M)

Итоговая O(N * M) + O(N * M) -> O(N * M)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
N - количество строк;
M - максимальная длина строки на входе; (~ длине распакованной строки)

В процессе вычисления в памяти всегда лежит префикc -> O(M),
Распаковываемая строка -> O(M);
Распакованная строка -> O(M);
Рекурсивное вычисление запакованной строки в худшем случае с большой вложенностью([[[[[]]]]]):
n - минимальное количество символов запакованной строки ~ 4   (2[a])
Хранение строки и подстрок в пике рекурсии M + M - n + M - 2n + M - 3n + .. + n -> M
Стек M/4 + (M -n)/4 .... -> M
Итого O(M) + O(M) + O(M) + O(M) -> O(M)
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/*
A. Packed Prefix
 */
public class APackedPrefix {

    private final static Set<Character> numbers = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9');
    private final static char OPEN_BRACKET = '[';
    private final static char CLOSE_BRACKET = ']';


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            String result = readInputAndCount(reader);

            printResult(writer, result);
        }
    }

    private static void printResult(PrintWriter writer, String prefix) {
        writer.println(prefix);
    }

    private static String readInputAndCount(BufferedReader reader) throws IOException {
        int lineCount = Integer.parseInt(reader.readLine());

        String prefix = null;
        for (int i = 0; i < lineCount; i++) {
            String line = unpackLine(reader.readLine());
            prefix = line.substring(0, cropCommonPrefixLength(line, prefix));
        }
        return prefix;
    }

    private static int cropCommonPrefixLength(String line, String curPrefix) {
        if (Objects.isNull(curPrefix)) {
            return line.length();
        }

        for (int i = 0; i < curPrefix.length(); i++) {
            if (line.charAt(i) != curPrefix.charAt(i)) {
                return i;
            }
        }
        return curPrefix.length();
    }

    private static String unpackLine(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (numbers.contains(line.charAt(i))) {
                int endIndex = countEndIndex(line, i + 1);
                sb.append(multiplyContent(
                        Character.getNumericValue(line.charAt(i)),
                        unpackLine(line.substring(i + 1, endIndex + 1))));
                i = endIndex;
            } else if (line.charAt(i) != OPEN_BRACKET && line.charAt(i) != CLOSE_BRACKET) {
                sb.append(line.charAt(i));
            }
        }
        return sb.toString();
    }

    private static String multiplyContent(int miltiplier, String content) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < miltiplier; i++) {
            sb.append(content);
        }
        return sb.toString();
    }

    private static int countEndIndex(String line, int startIndex) {
        Stack<Character> brackets = new Stack<>();
        for (int i = startIndex; i < line.length(); i++) {
            if (line.charAt(i) == OPEN_BRACKET) {
                brackets.push(line.charAt(i));
            } else if (line.charAt(i) == CLOSE_BRACKET) {
                brackets.pop();
                if (brackets.isEmpty()) {
                    return i;
                }
            }
        }
        return -1; // не достижимо, при правильных данных
    }
}
