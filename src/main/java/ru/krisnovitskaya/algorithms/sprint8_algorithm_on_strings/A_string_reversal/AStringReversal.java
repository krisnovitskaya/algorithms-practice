package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.A_string_reversal;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/*
A. Разворот строки
 */
public class AStringReversal {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            Stack<String> stack = new Stack<>();
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()){
                stack.push(tokenizer.nextToken());
            }

            StringBuilder  sb = new StringBuilder();
            int length = stack.size();

            for (int i = 0; i < length; i++) {
                sb.append(stack.pop()).append(" ");
            }

            writer.println(sb);
        }
    }
}
