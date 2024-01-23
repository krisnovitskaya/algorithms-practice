package ru.krisnovitskaya.algorithms.sprint1;
/*
I. Степень четырёх
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Напишите программу, которая определяет, будет ли положительное целое число степенью четвёрки.

Подсказка: степенью четвёрки будут все числа вида 4n, где n – целое неотрицательное число.

Формат ввода
На вход подаётся целое число в диапазоне от 1 до 10000.

Формат вывода
Выведите «True», если число является степенью четырёх, «False» –— в обратном случае.

Пример 1
Ввод	Вывод
15
False
Пример 2
Ввод	Вывод
16
True

 */
import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int value = scanner.nextInt();
            boolean res = evaluate(value);
            if(res){
                System.out.println("True");
            }else{
                System.out.println("False");
            }
        }
    }

    private static boolean evaluate(int input) {
        if(input == 1 || input == 4) {
            //степень 0 и 1
            return true;
        }
        int retain = 0;
        int divided = input;
        while (retain == 0){
            retain = divided % 4;
            divided = divided / 4;
            if(divided == 4) {
                return true;
            }
        }
        return false;
    }
}
