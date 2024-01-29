package ru.krisnovitskaya.algorithms.sprint2.finals;
// https://contest.yandex.ru/contest/22781/run-report/105837862/

import java.io.*;
import java.util.Stack;

/*
B. Калькулятор
/* -- ПРИНЦИП РАБОТЫ --
Для реализации калькулятора с обратной польской нотацией был использован алгоритм на основании стека описанный в формулировке задачи.


Входные данные преобразуются в массив и в цикле выполняется определение действия согласно текущему элементу операнду или знаку операции.
Операнды кладутся в структуру данных стек, что позволяет при появлении знака операции извлечь 2 операнда из стека, где последний это левый операнд, а предпоследний — правый, и применить к ним необходимую операцию. Результат операции кладется в стек, для использования с дальнейшими знаками операции. При появлении знака операнда в стеке всегда присутствуют минимум 2 значения. В конце в стеке должен остаться только результат вычисления.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ
Добавление и извлечение данные из стека стоит O(1), определение операндов/знаков операции и вычисление происходит за константное время и не зависит от размера данных. Итоговая временная сложность определяется количеством входных данных, по которому идет итерация. Поэтому итоговая временная сложность будет O(N), где N – количество операндов и знаков операций.

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность определяется количеством сохраненных данных, в данном случае память используют массив операндов и знаков = O(N) и стек, наполненность которого в худшем случае примерно O(0,5N).
Поэтому, в итоге пространственная сложность равно O(N) + O(0,5N) = O(1,5N) ~ O(N)
 */
public class BFinal {

    private static String MULTIPLY = "*";
    private static String DIVIDE = "/";
    private static String SUBTRACT = "-";
    private static String ADD = "+";
    private static String SPLITTER = " ";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            String calcExpression = reader.readLine();

            int result = calculate(calcExpression);
//
            writer.write(String.valueOf(result));
        }
    }

    private static int calculate(String calcExpression) {

        String[] expression = calcExpression.split(SPLITTER);

        Stack<Integer> values = new Stack<>();

        Integer left;
        Integer right;

        for (int i = 0; i < expression.length; i++) {
            if (MULTIPLY.equals(expression[i])) {
                right = values.pop();
                left = values.pop();
                values.push(left * right);
            } else if (DIVIDE.equals(expression[i])) {
                right = values.pop();
                left = values.pop();
                values.push(Math.floorDiv(left, right));
            } else if (ADD.equals(expression[i])) {
                right = values.pop();
                left = values.pop();
                values.push(left + right);
            } else if (SUBTRACT.equals(expression[i])) {
                right = values.pop();
                left = values.pop();
                values.push(left - right);
            } else {
                values.push(Integer.parseInt(expression[i]));
            }
        }
        return values.pop();
    }
}
