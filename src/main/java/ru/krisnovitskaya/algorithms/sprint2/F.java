package ru.krisnovitskaya.algorithms.sprint2;


/*
F. Стек - Max
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Нужно реализовать класс StackMax, который поддерживает операцию определения максимума среди всех элементов в стеке.
Класс должен поддерживать операции push(x), где x – целое число, pop() и get_max().

Формат ввода
В первой строке записано одно число n — количество команд, которое не превосходит 10000. В следующих n строках идут команды. Команды могут быть следующих видов:

push(x) — добавить число x в стек. Число x не превышает 105;
pop() — удалить число с вершины стека;
get_max() — напечатать максимальное число в стеке;
Если стек пуст, при вызове команды get_max() нужно напечатать «None», для команды pop() — «error».

Формат вывода
Для каждой команды get_max() напечатайте результат её выполнения.
 Если стек пустой, для команды get_max() напечатайте «None».
  Если происходит удаление из пустого стека — напечатайте «error».

 */

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

public class F {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            int commandLength = Integer.parseInt(reader.readLine());

            StackMax stackMax = new StackMax(writer, commandLength);

            process(stackMax, reader, commandLength);
        }
    }

    private static void process(StackMax stackMax, BufferedReader reader, int commandLength) throws IOException {
        String line;
        for (int i = 0; i < commandLength; i++) {
            line = reader.readLine();
            if(line.startsWith("push")){
                stackMax.push(Integer.parseInt(line.split(" ")[1]));
            }else if(line.equals("pop")){
                stackMax.pop();
            }else { // get_max
                stackMax.printMax();
            }
        }
    }


    public static class StackMax {
        private int[] data;
        private int size;
        private BufferedWriter writer;


        public StackMax(BufferedWriter writer, int capacity){
            this.writer = writer;
            this.data = new int[capacity];
            this.size = 0;
        }

        public void push(int value){
            data[size++] = value;
        }

        public void pop() throws IOException {
            if(size == 0){
                writer.write("error");
                writer.newLine();
            } else {
                size--;
            }
        }

        public void printMax() throws IOException {
            if(size == 0){
                writer.write("None");
                writer.newLine();
                return;
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                if(data[i] > max){
                    max = data[i];
                }
            }
            writer.write(String.valueOf(max));
            writer.newLine();
        }
    }
}
