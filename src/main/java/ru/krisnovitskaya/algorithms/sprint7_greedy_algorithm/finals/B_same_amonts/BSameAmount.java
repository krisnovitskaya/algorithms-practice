package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.finals.B_same_amonts;
// https://contest.yandex.ru/contest/25597/run-report/110841340/

/* -- ПРИНЦИП РАБОТЫ --
Алгоритм работы представляет из себя:
Расчет вероятностей получения требуемой суммы из ряда чисел nums при помощи матрицы dp[nums.length+1][halfsum+1] (сокращенной
в итоге до двух массивов dp[halfsum+1] и previousDp[halfsum +1]);
базовый случай dp[0] = true что из любого набора чисел мы можем получить сумму = 0 , не взяв ни одно число из текущего набора.
формула перехода
dp[j] = dpPrev[j] для случая, когда текущее проверяемое(добавляемое к последовательности)
значение nums[i - 1] > j, где j текущий искомый максимум. Значит добавляя nums[i - 1] мы точно превысим текущий максимум,
 поэтому не берем число и берем вероятность получить искомую сумму с прошлого комплекта.
или
dp[j] = dpPrev[j - numbers[i - 1]] || dpPrev[j] то есть мы либо не берем текущее значение в выборку,
 так как предыдущий набор нам уже дал искомую сумму, либо проверяем добавляя nums[i - 1] к предыдущему значению
 вероятности  с суммой меньшей на nums[i - 1] -> dpPrev[j - numbers[i - 1]]

В итоге искомый ответ будет в dpPrev[halfsum], либо найдется раньше, если при переборе nums встретися элемент>=halfsum
-- ВРЕМЕННАЯ СЛОЖНОСТЬ
N - число партий (длина входящего массива значений)
1)Подсчет суммы -> O(N);
2)Итерации по массиву дп
O(N * Sum(N)/2) -> O(N^2)

Итоговая O(N) + O(N^2) -> O(N(N + 1)) -> O(N^2)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
N - число партий (длина входящего массива значений)
1) Хранение массива чисел  -> O(N)
2) В единый момент времени 2 массива для рассчета дп длиной sum(N)/2 +1
O(sum(N)/2 +1) + O(sum(N)/2 +1) -> O(N) + O(N) -> O(N)

Итоговая  O(N) + O(N) -> O(N)
 */



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
B. Одинаковые суммы
 */
public class BSameAmount {
    private static final String TRUE = "True";
    private static final String FALSE = "False";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            int[] numbers = readData(reader);

            boolean result = isSameAmounts(numbers);
            printResult(result, writer);
        }
    }

    private static void printResult(boolean result, PrintWriter writer) {
        if (result) {
            writer.println(TRUE);
        } else {
            writer.println(FALSE);
        }
    }

    private static int[] readData(BufferedReader reader) throws IOException {
        int numCount = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] numbers = new int[numCount];

        for (int i = 0; i < numCount; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return numbers;
    }

    private static boolean isSameAmounts(int[] numbers) {
        int sum = Arrays.stream(numbers).sum();

        if (sum % 2 != 0) {
            return false;
        }

        int halfSum = sum / 2;

        boolean[] dp = makeDefaultDp(halfSum + 1);
        boolean[] dpPrev = makeDefaultDp(halfSum + 1);

        for (int i = 1; i <= numbers.length; i++) {
            if(numbers[i - 1] == halfSum){
                return true;
            } else if (numbers[i - 1] > halfSum) {
                return false;
            }
            for (int j = 1; j <= halfSum; j++) {
                if(j - numbers[i - 1] >= 0){
                    dp[j] = dpPrev[j - numbers[i - 1]] || dpPrev[j];
                }else {
                    dp[j] = dpPrev[j];
                }
            }
            boolean [] temp = dp;
            dpPrev = dp;
            dp = temp;
        }
        return dpPrev[halfSum];
    }

    private static boolean[] makeDefaultDp(int length){
        boolean[] dp = new boolean [length];
        dp[0] = true;
        return dp;
    }
}