package ru.krisnovitskaya.algorithms.sprint5_trees.finals.A_heapsort;
// https://contest.yandex.ru/contest/24810/run-report/108277678/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* -- ПРИНЦИП РАБОТЫ --
Для реализации задачи согласно условию реализована структура бинарной кучи BinaryHeapMax типа max_heap.
Алгоритм сортировки при добавлении и удалении элементов реализован на основе теории в практикуме.
При добавлении используется просеивание вверх, при удалении просеивание вниз.
Поэтому получение результата задачи состоит в последовательном добавлении в кучу значений,а потом последовательного
извлечения текущего максимума.
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм работает в худшем случае за O(NlogN) + O(NlogN) -> O(NlogN)
Состоит из добавления с просеиванием ( N раз за высоту logN)  и последующим извлечением с просеиванием ( N раз за высоту logN)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
O(N) пространственная сложность, в основе бинарной кучи лежит массив, поэтому на максимуме пространственная сложность
 прямо пропорциональна длине массива
 */

/*
A. Пирамидальная сортировка
 */
public class Afinal {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            int count = Integer.parseInt(reader.readLine());
            BinaryHeapMax<Participant> heap = fillHeap(reader, count);

            String result = getResult(heap);
            writer.write(result);
        }
    }

    private static String getResult(BinaryHeapMax<Participant> heap) {
        StringBuilder result = new StringBuilder();
        while (0 < heap.size()) {
            result.append(heap.popMax().getLogin()).append("\n");
        }
        return result.toString();
    }

    private static BinaryHeapMax<Participant> fillHeap(BufferedReader reader, int count) throws IOException {
        BinaryHeapMax<Participant> heap = new BinaryHeapMax<>(count);
        for (int i = 0; i < count; i++) {
            String[] lines = reader.readLine().split(" ", 3);
            heap.heapAdd(new Participant(lines[0], Integer.parseInt(lines[1]), Integer.parseInt(lines[2])));
        }
        return heap;
    }

    static class BinaryHeapMax<T extends Comparable<T>> {
        private List<T> data;

        public BinaryHeapMax(int startedSize) {
            this.data = new ArrayList<>(startedSize + 1);
            this.data.add(null);
        }

        public int size(){
            return data.size() - 1;
        }

        public void heapAdd(T element){
            data.add(element);
            siftUp(data.size() - 1);
        }

        public T popMax() {
            if(data.size() <= 1){
                throw new IllegalArgumentException();
            }
            T result = data.get(1);
            data.set(1, data.get(data.size() - 1));
            data.remove(data.size() - 1);
            siftDown( 1);
            return result;
        }
        private void siftUp(int index) {
            if (index == 1) {
                return;
            }

            int parentIndex = index / 2;
            if (data.get(parentIndex).compareTo(data.get(index)) < 0) {
                T temp = data.get(parentIndex);
                data.set(parentIndex, data.get(index));
                data.set(index, temp);
                siftUp(parentIndex);
            }
        }

        private void siftDown(int index) {
            int left = 2 * index;
            int right = 2 * index + 1;

            if (left >= data.size()) {
                return;
            }

            int indexLargest = left;
            if (right < data.size() && data.get(right).compareTo(data.get(left)) > 0) {
                indexLargest = right;
            }

            if (data.get(indexLargest).compareTo(data.get(index)) > 0) {
                T temp = data.get(index);
                data.set(index, data.get(indexLargest));
                data.set(indexLargest, temp);
                siftDown(indexLargest);
            }
        }
    }

    static class Participant implements Comparable<Participant> {
        private String login;
        private int taskCount;
        private int penalty;

        public Participant(String login, int taskCount, int penalty) {
            this.login = login;
            this.taskCount = taskCount;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Participant o) {
            int taskCompare = -Integer.compare(o.taskCount, this.taskCount);
            if (taskCompare == 0) {
                int penaltyCompare = -Integer.compare(this.penalty, o.penalty);
                if (penaltyCompare == 0) {
                    return -String.CASE_INSENSITIVE_ORDER.compare(this.login, o.login);
                } else {
                    return penaltyCompare;
                }
            } else {
                return taskCompare;
            }
        }

        public String getLogin() {
            return login;
        }

        public int getTaskCount() {
            return taskCount;
        }

        public int getPenalty() {
            return penalty;
        }
    }
}
