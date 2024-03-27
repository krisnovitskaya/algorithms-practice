package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.C_golden_fever;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CGoldenFever {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            int bagLimit = Integer.parseInt(reader.readLine());
            int heapCount = Integer.parseInt(reader.readLine());

            List<HeapProperty> heaps =  new ArrayList<>();
            for (int i = 0; i < heapCount; i++) {
                String[] s = reader.readLine().split(" ", 2);
                heaps.add(new HeapProperty(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }

            heaps.sort(Comparator.naturalOrder());

            long bagPrice = 0;
            int curBagWeight = 0;
            int heapsIndex = 0;

            while (curBagWeight < bagLimit && heapsIndex < heaps.size()){
                HeapProperty heapProperty = heaps.get(heapsIndex);
                if(bagLimit - curBagWeight >= heapProperty.getWeight()){
                    bagPrice = bagPrice + heapProperty.getPrice() * heapProperty.getWeight();
                    heapsIndex++;
                    curBagWeight = curBagWeight + heapProperty.getWeight();
                    heapProperty.setWeight(0);
                } else {
                    int addedWeight = bagLimit - curBagWeight;
                    bagPrice = bagPrice + heapProperty.getPrice() * addedWeight;
                    curBagWeight = curBagWeight + addedWeight;
                    heapProperty.setWeight(heapProperty.getWeight() - addedWeight);
                }
            }

            writer.println(bagPrice);
        }
    }

    static class HeapProperty implements Comparable<HeapProperty>{
        long price;
        int weight;

        public long getPrice() {
            return price;
        }

        public int getWeight() {
            return weight;
        }

        public HeapProperty(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(HeapProperty o) {
            return -Long.compare(price, o.price);
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
