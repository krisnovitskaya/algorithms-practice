package ru.krisnovitskaya.algorithms.sprint2;


/*
G. Стек - MaxEffective
OpenJDK Java 11	1.5 секунд	64Mb
Реализуйте класс StackMaxEffective, поддерживающий операцию определения максимума среди элементов в стеке.
 Сложность операции должна быть O(1). Для пустого стека операция должна возвращать None.
  При этом push(x) и pop() также должны выполняться за константное время.

Формат ввода
В первой строке записано одно число — количество команд, оно не превосходит 100000.
 Далее идут команды по одной в строке. Команды могут быть следующих видов:

push(x) — добавить число x в стек. Число x не превышает 10^5;
pop() — удалить число с вершины стека;
get_max() — напечатать максимальное число в стеке;
top() — напечатать число с вершины стека;
Если стек пуст, при вызове команды get_max нужно напечатать «None», для команды pop и top — «error».
Формат вывода
Для каждой команды get_max() напечатайте результат её выполнения.
Если стек пустой, для команды get_max() напечатайте «None».
 Если происходит удаление из пустого стека — напечатайте «error».


 */

import java.io.*;

public class G {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            int commandLength = Integer.parseInt(reader.readLine());

            StackMaxEffective stackMax = new StackMaxEffective(writer, commandLength);

            process(stackMax, reader, commandLength);
        }
    }

    private static void process(StackMaxEffective stackMax, BufferedReader reader, int commandLength) throws IOException {
        String line;
        for (int i = 0; i < commandLength; i++) {
            line = reader.readLine();
            if(line.startsWith("push")){
                stackMax.push(Integer.parseInt(line.split(" ")[1]));
            }else if(line.equals("pop")) {
                stackMax.pop();
            } else if(line.equals("top")){
                stackMax.top();
            }else { // get_max
                stackMax.printMax();
            }
        }
    }


    public static class StackMaxEffective {
        private int[] data;
        private int[] maxValues;
        private int size;
        private BufferedWriter writer;


        public StackMaxEffective(BufferedWriter writer, int capacity){
            this.writer = writer;
            this.data = new int[capacity];
            this.maxValues = new int[capacity];
            this.size = 0;
        }

        public void push(int value){
            if(size == 0){
                maxValues[size] = value;
            }else {
                maxValues[size] = Math.max(maxValues[size - 1], value);
            }
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

        public void top() throws IOException {
            if(size == 0){
                writer.write("error");
                writer.newLine();
            } else {
                writer.write(String.valueOf(data[size - 1]));
                writer.newLine();
            }
        }

        public void printMax() throws IOException {
            if(size == 0){
                writer.write("None");
                writer.newLine();
            } else {
                writer.write(String.valueOf(maxValues[size - 1]));
                writer.newLine();
            }
        }
    }
}
