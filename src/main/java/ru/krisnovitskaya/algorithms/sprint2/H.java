package ru.krisnovitskaya.algorithms.sprint2;


/*
H. Скобочная последовательность
OpenJDK Java 11	1 секунда	64Mb
Дана скобочная последовательность. Нужно определить, правильная ли она.

Будем придерживаться такого определения:

пустая строка —– правильная скобочная последовательность;
правильная скобочная последовательность, взятая в скобки одного типа, –— правильная скобочная последовательность;
правильная скобочная последовательность с приписанной слева или справа правильной скобочной последовательностью —– тоже правильная.
На вход подаётся последовательность из скобок трёх видов: [], (), {}.
Напишите функцию, которая принимает на вход скобочную последовательность и возвращает True,
 если последовательность правильная, а иначе False.
 */

import java.io.*;
import java.util.Set;
import java.util.Stack;

public class H {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            char[] chars = readChars(reader);
            String result = isCorrectBracketsSeq(chars);
            writer.write(result);
        }
    }

    private static char[] readChars(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if(line == null || line.isBlank()){
            return new char[0];
        }
        return line.trim().toCharArray();
    }

    private static String isCorrectBracketsSeq(char[] chars) {
        if(chars.length == 0){
            return "True";
        }
        if(chars.length % 2 != 0){
            return "False";
        }

        Set<Character> rightBrackets = Set.of('{', '(', '[');
        if(!rightBrackets.contains(chars[0])){
            return "False";
        }

        Stack<Character> stack = new Stack<>();

        boolean flagEnd = false;
        for (int i = 0; i < chars.length; i++) {
            if(flagEnd){
                break;
            }
            switch (chars[i]){
                case '{':
                case '(':
                case '[':
                    stack.push(chars[i]);
                    break;
                case '}':
                    if(stack.empty() || stack.pop() != '{') {
                        flagEnd = true;
                    }
                    break;
                case ']':
                    if(stack.empty() || stack.pop() != '[') {
                        flagEnd = true;
                    }
                    break;
                case ')':
                    if(stack.empty() || stack.pop() != '(') {
                        flagEnd = true;
                    }
                    break;
            }
        }

        return flagEnd ? "False" : "True";
    }
}
