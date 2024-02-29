package ru.krisnovitskaya.algorithms.sprint5_trees.L_sift_down;

public class Solution {
    public static int siftDown(int[] heap, int idx) {
        int left = 2 * idx;
        int right = 2 * idx + 1;

        // Нет дочерних узлов
        if (left >= heap.length) {
            return idx;
        }

        // проверяем, что есть оба дочерних узла
        int indexLargest = left;
        if (right < heap.length && heap[right] > heap[left]) {
            indexLargest = right;
        }

        if (heap[indexLargest] > heap[idx]) {
            int temp = heap[idx];
            heap[idx] = heap[indexLargest];
            heap[indexLargest] = temp;
            return siftDown(heap, indexLargest);
        }
        return idx;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        System.out.println(siftDown(sample, 2) == 5);
    }

    public static void main(String[] args) {
        test();
    }
}