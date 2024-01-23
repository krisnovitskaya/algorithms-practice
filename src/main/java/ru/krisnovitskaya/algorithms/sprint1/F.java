package ru.krisnovitskaya.algorithms.sprint1;


/*
F. Палиндром
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Помогите Васе понять, будет ли фраза палиндромом‎. Учитываются только буквы и цифры, заглавные и строчные буквы считаются одинаковыми.

Решение должно работать за O(N), где N — длина строки на входе.

Формат ввода
В единственной строке записана фраза или слово. Буквы могут быть только латинские. Длина текста не превосходит 20000 символов.

Фраза может состоять из строчных и прописных латинских букв, цифр, знаков препинания.

Формат вывода
Выведите «True», если фраза является палиндромом, и «False», если не является.

Пример 1
Ввод	Вывод
A man, a plan, a canal: Panama
True
Пример 2
Ввод	Вывод
zo
False

 */

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;
public class F {
    private static Set<Character> chars = "abcdefghijklmnopqrstuvwxwz0123456789".chars().mapToObj(i -> (char) i).collect(Collectors.toSet());

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            char[] input = prepare(reader);


            boolean res = evaluate(input);
            if(res){
                writer.write("True");
            }else{
                writer.write("False");
            }
        }
    }

    private static char[] prepare(BufferedReader reader) throws IOException{
        return reader.readLine().toLowerCase().toCharArray();
    }

    private static boolean evaluate(char[] input) {
        if(input.length <= 1){
            return false;
        }

        int head = 0;
        int tail = input.length - 1;

        while (head < input.length && tail >= 0){
            if (!chars.contains(input[head])){
                head++;
            }else if (!chars.contains(input[tail])){
                tail--;
            }else if(input[head] == input[tail]){
                head++;
                tail--;
            }else {
                return false;
            }
        }
        return true;
    }
}
