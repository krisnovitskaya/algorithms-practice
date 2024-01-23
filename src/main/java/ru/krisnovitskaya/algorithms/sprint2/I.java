package ru.krisnovitskaya.algorithms.sprint2;

/*
I. Ограниченная очередь
Ограничение времени	0.3 секунды
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Астрологи объявили день очередей ограниченного размера. Тимофею нужно написать класс MyQueueSized, который принимает параметр max_size, означающий максимально допустимое количество элементов в очереди.

Помогите ему —– реализуйте программу, которая будет эмулировать работу такой очереди. Функции, которые надо поддержать, описаны в формате ввода.

Формат ввода
В первой строке записано одно число — количество команд, оно не превосходит 5000.
Во второй строке задан максимально допустимый размер очереди, он не превосходит 5000.
Далее идут команды по одной на строке. Команды могут быть следующих видов:

push(x) — добавить число x в очередь;
pop() — удалить число из очереди и вывести на печать;
peek() — напечатать первое число в очереди;
size() — вернуть размер очереди;
При превышении допустимого размера очереди нужно вывести «error». При вызове операций pop() или peek() для пустой очереди нужно вывести «None».
Формат вывода
Напечатайте результаты выполнения нужных команд, по одному на строке.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class I {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            int commandLength = readInt(reader);
            int queueSize = readInt(reader);

            List<String> commandList = readCommands(reader, commandLength);

            processCommands(commandList, queueSize, writer);
        }
    }

    private static void processCommands(List<String> commandList, int queueSize, BufferedWriter writer) throws IOException{
        MyQueueSized queue = new MyQueueSized(queueSize, writer);

        for (String command : commandList) {
            if(command.startsWith("push")){
                queue.push(Integer.parseInt(command.split(" ")[1]));
            }else if(command.equals("pop")) {
                queue.pop();
            } else if(command.equals("peek")){
                queue.peek();
            }else { // size
                queue.size();
            }
        }
    }

    private static List<String> readCommands(BufferedReader reader, int commandLength) throws IOException {
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < commandLength; i++) {
            commands.add(reader.readLine());
        }
        return commands;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static class MyQueueSized {

        private static String NONE = "None";
        private static String ERROR = "error";
        private Integer[] data;
        private int size;
        private int head;
        private int tail;

        private BufferedWriter writer;

        public MyQueueSized(int maxSize, BufferedWriter writer){
            this.data = new Integer[maxSize];
            this.size = 0;
            this.tail = 0;
            this.head = 0;
            this.writer = writer;
        }

        public void push(int value) throws IOException {
            if(size == data.length){
                printLine(ERROR);
            }else {
                data[tail] = value;
                tail = (tail + 1) % data.length;
                size++;
            }
        }

        public void pop() throws IOException {
            if(isEmpty()){
                printLine(NONE);
            } else {
                Integer val = data[head];
                data[head] = null;
                head = (head + 1) % data.length;
                size--;
                printLine(String.valueOf(val));
            }
        }

        public void peek() throws IOException {
            if(isEmpty()){
                printLine(NONE);
            } else {
                printLine(String.valueOf(data[head]));
            }
        }

        public void size() throws IOException {
            printLine(String.valueOf(size));
        }


        private boolean isEmpty(){
            return size == 0;
        }

        private void printLine(String value) throws IOException {
            writer.write(value);
            writer.newLine();
        }
    }
}
