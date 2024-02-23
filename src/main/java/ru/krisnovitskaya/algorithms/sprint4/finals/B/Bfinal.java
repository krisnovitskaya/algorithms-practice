package ru.krisnovitskaya.algorithms.sprint4.finals.B;
// https://contest.yandex.ru/contest/24414/run-report/107532448/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/* -- ПРИНЦИП РАБОТЫ --
Для реализации кастомной хэштаблицы была использована структура данных на основе массива. Количество корзин вычисляется
как первое наименьшее просто число относительно числа комманд Номер корзины высчитывается методом деления по ключу.
indexBucket= abs(hash(key)) mod BucketSize
Функция рассчета хэша использоана стандартная джавовская, а для отрицательных значений берется значение хэша по модулю.
Для решения коллизий использован принцип цепочек.
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность работы с хэштаблицей в среднем случае константно и равно O(1), так как все операции вычислительные,
единственные итерации в случае прохода по цепочкам коллизий, но при хорошем распределении по бакетам - итерации небольшие.
Если учитывать временную сложность алгоритма задачи в целом, то сложность будет равна O(N), так как идет считывание
из ресурса и обработка в цикле.
-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
Так как реализация по условию задачи не предполагает рехеширования, созданный объем бакетов примерно равен колиеству команд.
И в худшем случае, когда были только добавления в  таблицу, и все ключи разные будет занимать O(N)
 */
/*
B. Хеш-таблица
 */
public class Bfinal {
    private static String GET = "get";
    private static String PUT = "put";
    private static String DELETE = "delete";
    private static String NONE = "None";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            int commandCount = Integer.parseInt(reader.readLine());
            processData(reader, writer, commandCount);
        }
    }

    private static void processData(BufferedReader reader, PrintWriter writer, int commandCount) throws IOException {
        SimpleHashTable<Integer, Integer> hashtable = new SimpleHashTable<>(commandCount);
        String command;
        String[] parts;
        Integer value;
        for (int i = 0; i < commandCount; i++) {
            command = reader.readLine();
            if (command.startsWith(GET)) {
                parts = command.split(" ", 2);
                value = hashtable.get(Integer.parseInt(parts[1]));
                writer.println(value == null ? NONE : value);
            } else if (command.startsWith(PUT)) {
                parts = command.split(" ", 3);
                hashtable.put(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            } else { //delete
                parts = command.split(" ", 2);
                value = hashtable.delete(Integer.parseInt(parts[1]));
                writer.println(value == null ? NONE : value);
            }
        }
    }


    static class SimpleHashTable<K, V> {
        private int defaultBucketSize;
        private Entry<K, V>[] data;

        public SimpleHashTable(int commandSize) {
            defaultBucketSize = countDefaultSize(commandSize);
            data = (Entry<K, V>[]) new Entry[defaultBucketSize];
        }

        public V put(K key, V value) {
            Entry<K, V> newEntry = new Entry<>(key, value);
            int bucket = bucket(key);

            Entry<K, V> first = data[bucket];
            if (first == null) {
                data[bucket] = newEntry;
                return null;
            }

            if (first.key.equals(key)) {
                V oldValue = first.value;
                first.value = value;
                return oldValue;
            }

            Entry<K, V> next = first.next;

            while (next != null) {
                if (next.key.equals(key)) {
                    V oldValue = next.value;
                    next.value = value;
                    return oldValue;
                }
                first = next;
                next = next.next;
            }
            first.next = newEntry;
            return null;
        }

        public V get(K key) {
            int bucket = bucket(key);
            Entry<K, V> first = data[bucket];
            if (first == null) {
                return null;
            }

            do {
                if (first.key.equals(key)) {
                    return first.value;
                }
                first = first.next;
            } while (first != null);
            return null;
        }

        public V delete(K key) {
            int bucket = bucket(key);
            Entry<K, V> first = data[bucket];
            if (first == null) {
                return null;
            }

            if (first.key.equals(key)) {
                V oldValue = first.value;
                data[bucket] = first.next;
                return oldValue;
            }

            Entry<K, V> next = first.next;

            while (next != null) {
                if (next.key.equals(key)) {
                    V oldValue = next.value;
                    first.next = next.next;
                    return oldValue;
                }
                first = next;
                next = next.next;
            }
            return null;
        }


        private int bucket(K key) {
            return hash(key) % defaultBucketSize;
        }

        private int hash(K key){
            return Math.abs(key.hashCode());
        }

        private int countDefaultSize(int commandSize) {
            if(commandSize <= 2 ){
                return commandSize;
            }
            for (int i = commandSize; i >= 2; i--) {
                if(isPrime(i)){
                    return i;
                }
            }
            return commandSize;
        }

        public static boolean isPrime(int n) {
            if (n == 1) {
                return false;
            }
            int i = 2;
            while (i * i <= n) {
                if (n % i == 0) {
                    return false;
                }
                i = i + 1;
            }
            return true;
        }

        private class Entry<K, V> {
            private K key;
            private V value;
            private Entry<K, V> next;

            Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
