package ru.krisnovitskaya.algorithms.sprint2.finals;
// https://contest.yandex.ru/contest/22781/run-report/106090099/


/*
A. Дек
 -- ПРИНЦИП РАБОТЫ --
Для реализации дека фиксированного размера был использован массив в качестве кольцевого буфера — требования ТЗ

Добавление и извлечение элементов происходит по индексам головы и хвоста дэка.
При добавлении значения в начало пишется в ячейку head-1, индекс смешается на -1.
При добавлении значения в конец пишется в ячейку tail+1, индекс смешается на +1.
В случае добавления в пустую очередь производится проверка и значение записываются по 0 индексу массива с данными,
 индексы обновляются head=tail=0.
При извлечении значение достается по текущему индексу голову или хвоста, индексы соответственно увеличиваются или уменьшаются на 1

-- ВРЕМЕННАЯ СЛОЖНОСТЬ
Добавление и извлечение данные из массива стоит O(1), вычисление индексов происходит за константное время и не зависит
 от размера данных, поэтому итоговая временная сложность тоже O(1)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Дек ограниченного размера N на основании массива, поэтому в худшем случае заполнен полностью и потребляет O(N) памяти
 */

import java.io.*;
import java.util.NoSuchElementException;

public class AFinal3 {
    private static String PUSH_BACK = "push_back";
    private static String PUSH_FRONT = "push_front";
    private static String POP_BACK = "pop_back";
    private static String POP_FRONT = "pop_front";
    private static String SPLITTER = " ";

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            processCommands(reader, writer);
        }
    }

    private static void processCommands(BufferedReader reader, BufferedWriter writer) throws IOException {
        int commandLength = readInt(reader);
        int dequeSize = readInt(reader);
        MyDequeCycled<Integer> deque = new MyDequeCycled<>(dequeSize);

        String command;
        for (int i = 0; i < commandLength; i++) {
            command = reader.readLine();
            if (command.startsWith(PUSH_BACK)) {
                try {
                    deque.pushBack(Integer.parseInt(command.split(SPLITTER)[1]));
                } catch (IllegalStateException e) {
                    printLine(e.getMessage(), writer);
                }
            } else if (command.startsWith(PUSH_FRONT)) {
                try {
                    deque.pushFront(Integer.parseInt(command.split(SPLITTER)[1]));
                } catch (IllegalStateException e) {
                    printLine(e.getMessage(), writer);
                }
            } else if (POP_BACK.equals(command)) {
                try {
                    printLine(String.valueOf(deque.popBack()), writer);
                } catch (NoSuchElementException e) {
                    printLine(e.getMessage(), writer);
                }
            } else { // POP_FRONT
                try {
                    printLine(String.valueOf(deque.popFront()), writer);
                } catch (NoSuchElementException e) {
                    printLine(e.getMessage(), writer);
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static void printLine(String value, BufferedWriter writer) throws IOException {
        writer.write(value);
        writer.newLine();
    }

    public static class MyDequeCycled<T> {
        private static String ERROR = "error";
        private T[] data;
        private int size;
        private int head;
        private int tail;


        public MyDequeCycled(int maxSize) {
            this.data = (T[]) new Object[maxSize];
            this.size = 0;
            this.head = 0;
            this.tail = 0;
        }

        public void pushBack(T value) {
            if (isFull()) {
                throw new IllegalStateException(ERROR);
            } else {
                if (isEmpty()) {
                    data[0] = value;
                    head = 0;
                    tail = 0;
                } else {
                    data[tail = (tail + 1) % data.length] = value;
                }
                size++;
            }
        }

        public void pushFront(T value) {
            if (isFull()) {
                throw new IllegalStateException(ERROR);
            } else {
                if (isEmpty()) {
                    data[0] = value;
                    head = 0;
                    tail = 0;
                } else {
                    data[head = (head - 1) >= 0 ? head - 1 : data.length - 1] = value;
                }
                size++;
            }
        }


        public T popBack() {
            if (isEmpty()) {
                throw new NoSuchElementException(ERROR);
            } else {
                T val = data[tail];
                data[tail--] = null;
                tail = tail >= 0 ? tail : data.length - 1;
                size--;
                return val;
            }
        }

        public T popFront() {
            if (isEmpty()) {
                throw new NoSuchElementException(ERROR);
            } else {
                T val = data[head];
                data[head] = null;
                head = (head + 1) % data.length;
                size--;
                return val;
            }
        }

        private boolean isEmpty() {
            return size == 0;
        }

        private boolean isFull() {
            return size == data.length;
        }

    }
}
