package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.finals.A_levenstein_distance;
// https://contest.yandex.ru/contest/25597/run-report/110732039/

/*
 -- ПРИНЦИП РАБОТЫ --
Для вычисления расстояния по Ленштейну в задаче реализован алгоритм Вагнера-Фишера
https://ru.wikipedia.org/wiki/%D0%A0%D0%B0%D1%81%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5_%D0%9B%D0%B5%D0%B2%D0%B5%D0%BD%D1%88%D1%82%D0%B5%D0%B9%D0%BD%D0%B0

Матрица dp[str.length +1][str2.length + 1]
В каждой ячейке на ересении содержит минимальное число преобразований еоторое мы можем совершить чтобы из текущей
подстроки1 сделать подстроку2
Базовые случаи когда одна из строк пуста, поэтому для перевода в другую надо добавить или удалить столько символов
сколько в не пустой строке

по индексам dp[i - 1][j] содержится информация о том, сколько действий надо будет сделать если сейчас мы будем удалять символ
по индексам dp[i][j -1] содержится информация о том, сколько действий надо будет сделать если сейчас мы будем добавлять символ
в dp[i  - 1][j - 1] сейчас мы делаем замену или ничего.

Чтобы определить какое действие эффективней, надо выбрать минимальное значение для вставок +1,удаление +1, замены +1

Искомый результат лежит в dp[str.length][str2.length]

-- ВРЕМЕННАЯ СЛОЖНОСТЬ
N длина первой строки, М длина второй строки.
Для вычисления результата используется цикл в цикле, поэтому временная сложность задачи будет равна O(N*M)

Итоговая O(N) + O(N^2) -> O(N(N + 1)) -> O(N^2)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
N длина первой строки, М длина второй строки.
массив dp занимает в памяти O(N*M)
Сами строки занимают так же O(N) и O(M)
Поэтому итоговая пространственная сложность равна = O(N) + O(M) + O(N*M) -> O(N*M)
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
A. Расстояние по Левенштейну
 */
public class ALevensteinDistance {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {

            int result = countLevensteinDistance(reader.readLine(), reader.readLine());
            writer.println(result);
        }
    }


    private static int countLevensteinDistance(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= str2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                dp[i][j] = Math.min(Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1)
                        , dp[i - 1][j - 1] + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1));
            }
        }

        return dp[str1.length()][str2.length()];
    }
}
