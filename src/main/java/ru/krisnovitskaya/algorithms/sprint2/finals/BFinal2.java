package ru.krisnovitskaya.algorithms.sprint2.finals;
// https://contest.yandex.ru/contest/22781/run-report/106052328/


/*
B. Калькулятор
/* -- ПРИНЦИП РАБОТЫ --
Для реализации калькулятора с обратной польской нотацией был использован алгоритм на основании стека описанный
 в формулировке задачи.

Входные данные в цикле считываются из файла и в этом же цикле выполняется определение действия согласно текущему
 элементу операнду или знаку операции.
Операнды кладутся в структуру данных стек, что позволяет при появлении знака операции извлечь 2 операнда из стека,
 где последний это левый операнд, а предпоследний — правый, и применить к ним необходимую операцию.
 Результат операции кладется в стек, для использования с дальнейшими знаками операции. При появлении знака операнда
  в стеке всегда присутствуют минимум 2 значения. В конце в стеке должен остаться только результат вычисления.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ
Добавление и извлечение данные из стека стоит O(1), определение операндов/знаков операции и вычисление происходит
 за константное время и не зависит от размера данных. Итоговая временная сложность определяется количеством входных
  данных, по которому идет итерация. Поэтому итоговая временная сложность будет O(N), где N – количество операндов
   и знаков операций.

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность определяется количеством сохраненных данных, в данном случае память использует стек,
 наполненность которого в худшем случае примерно O(0,5N).

Поэтому, в итоге пространственная сложность равно  O(0,5N) ~ O(N)
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class BFinal2 {

    private static String MULTIPLY = "*";
    private static String DIVIDE = "/";
    private static String SUBTRACT = "-";
    private static String ADD = "+";
    private static String SPLITTER = " ";

    public static void main(String[] args) throws IOException {


        try (Scanner scanner = new Scanner(new File("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            int result = readAndcalculate(scanner);

            writer.print(result);
        }
    }

    private static int readAndcalculate(Scanner scanner) {
        //scanner.useDelimiter(SPLITTER);
        /* при установке делиметра " " контест падает с переодическим NumberFormatException
        https://contest.yandex.ru/contest/22781/run-report/106051873/
        у меня на машине таких проблем с теми же данными нет.
        строковый сплит в контесте как в прошлой версии по " " ошибок не вызывал.
        особенности контеста?
        */
        scanner.useDelimiter(" ");
        Stack<Integer> values = new Stack<>();
        String expressionPart;
        Integer left;
        Integer right;
        while (scanner.hasNext()) {
            expressionPart = scanner.next();
            if (MULTIPLY.equals(expressionPart)) {
                right = values.pop();
                left = values.pop();
                values.push(left * right);
            } else if (DIVIDE.equals(expressionPart)) {
                right = values.pop();
                left = values.pop();
                values.push(Math.floorDiv(left, right));
            } else if (ADD.equals(expressionPart)) {
                right = values.pop();
                left = values.pop();
                values.push(left + right);
            } else if (SUBTRACT.equals(expressionPart)) {
                right = values.pop();
                left = values.pop();
                values.push(left - right);
            } else {
                values.push(Integer.parseInt(expressionPart));
            }
        }
        return values.pop();
    }
}
