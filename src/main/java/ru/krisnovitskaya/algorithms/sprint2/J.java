package ru.krisnovitskaya.algorithms.sprint2;

/*
J. Списочная очередь
Ограничение времени	0.3 секунды
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Любимый вариант очереди Тимофея — очередь, написанная с использованием связного списка. Помогите ему с реализацией. Очередь должна поддерживать выполнение трёх команд:

get() — вывести элемент, находящийся в голове очереди, и удалить его. Если очередь пуста, то вывести «error».
put(x) — добавить число x в очередь
size() — вывести текущий размер очереди
Формат ввода
В первой строке записано количество команд n — целое число, не превосходящее 1000. В каждой из следующих n строк записаны команды по одной строке.

Формат вывода
Выведите ответ на каждый запрос по одному в строке.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class J {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            int commandLength = readInt(reader);

            List<String> commandList = readCommands(reader, commandLength);

            processCommands(commandList, writer);
        }
    }

    private static void processCommands(List<String> commandList, BufferedWriter writer) throws IOException {
        MyLinkedQueue queue = new MyLinkedQueue( writer);

        for (String command : commandList) {
            if (command.startsWith("put")) {
                queue.put(Integer.parseInt(command.split(" ")[1]));
            } else if (command.equals("get")) {
                queue.get();
            } else { // size
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

    public static class MyLinkedQueue<T> {

        private static String ERROR = "error";
        private Node<T> head;
        private Node<T> tail;
        private int size;

        private BufferedWriter writer;

        public MyLinkedQueue(BufferedWriter writer) {
            this.size = 0;
            this.writer = writer;
        }

        public void put(T value) throws IOException {
            if (isEmpty()) {
                Node<T> newNode = new Node<>(value);
                head = newNode;
                tail = newNode;
            } else {
                Node<T> newNode = new Node<>(value);
                tail.next = newNode;
                tail = newNode;
            }
                size++;
        }

        public void get() throws IOException {
            if (isEmpty()) {
                printLine(ERROR);
            } else {
                Node<T> node = head;
                head = head.next;
                size--;
                if(size == 0){
                    head = tail = null;
                }
                printLine(String.valueOf(node.elem));
            }
        }


        public void size() throws IOException {
            printLine(String.valueOf(size));
        }


        private boolean isEmpty() {
            return size == 0;
        }

        private void printLine(String value) throws IOException {
            writer.write(value);
            writer.newLine();
        }

        private static class Node<T> {
            T elem;
            Node<T> next;

            Node(T elem) {
                this.elem = elem;
            }
        }
    }
}
