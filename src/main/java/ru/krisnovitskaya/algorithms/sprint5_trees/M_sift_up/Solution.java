package ru.krisnovitskaya.algorithms.sprint5_trees.M_sift_up;

public class Solution {
    //нулевой элемент есть всегда, но какой он совершенно неважно. Нигде не участвует
    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) {
            return 1;
        }

        int parentIndex = (idx) / 2 ;
        if (heap[parentIndex] < heap[idx]) {
            int temp = heap[parentIndex];
            heap[parentIndex] = heap[idx];
            heap[idx] = temp;
            return siftUp(heap, parentIndex);
        }
        return idx;
    }



    private static void test() {
        int[] sample2 = {-1, 12, 6, 8, 3, 4, 7};
        //5 11
        sample2[5] = sample2[5] + 11;
        System.out.println(siftUp(sample2, 5));
        print(sample2);
        //3 6
        sample2[3] = sample2[3] + 6;
        System.out.println(siftUp(sample2, 3));
        print(sample2);

        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        System.out.println(siftUp(sample, 5) == 1);
        print(sample);

        int[] sample3 = {-1,12, 6, 8, 3, 15, 7};
        System.out.println(siftUp(sample3,5) == 1);
        print(sample3);
    }
    private static void print(int[] sample){
        for (int i = 0; i < sample.length; i++) {
            System.out.print(sample[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test();
    }
}
