package ru.krisnovitskaya.algorithms.sprint4.G;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
G. Соревнование
 */
public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            int count = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            List<List<Integer>> arr = new ArrayList<>(count);
            Map<Integer, Count> map = new LinkedHashMap<>();
            Integer countFirst = 0;
            Integer countSecond = 0;
            int i = 0;
            arr.add(List.of(i, countFirst, countSecond, countFirst - countSecond));
            map.put(countFirst - countSecond, new Count(0, 0));
            i++;
            while (tokenizer.hasMoreTokens()) {
                Integer player = Integer.parseInt(tokenizer.nextToken());
                if (player == 0) {
                    ++countFirst;
                } else {
                    ++countSecond;
                }
                int finalI = i;
                map.merge(countFirst - countSecond, new Count(finalI, 0), (oldV, newV) -> {
                    return new Count(oldV.getStartIndex(), finalI - oldV.getStartIndex());
                });
                i++;
            }
            List<Count> c = new ArrayList<>(map.values());
            c.sort(Comparator.naturalOrder());
            writer.println(c.get(0).length);
        }
    }

    static class Count implements Comparable<Count> {
        int startIndex;
        int length;

        public Count(int startIndex, int length) {
            this.startIndex = startIndex;
            this.length = length;
        }

        public int getLength() {
            return length;
        }

        public int getStartIndex() {
            return startIndex;
        }

        @Override
        public int compareTo(Count o) {
            return -Integer.compare(this.length, o.length);
        }
    }
}
