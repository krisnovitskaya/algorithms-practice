package ru.krisnovitskaya.algorithms.sprint7_greedy_algorithm.B_schedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BSchedule {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            int count = Integer.parseInt(reader.readLine());

            List<Pair> data = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String[] s = reader.readLine().split(" ");
                data.add(new Pair(Double.parseDouble(s[0]), Double.parseDouble(s[1])));
            }

            data.sort(Comparator.naturalOrder());

            List<Pair> result = new ArrayList<>();

            result.add(data.get(0));
            for (int i = 1; i < data.size(); i++) {
                Pair lastInResult = result.get(result.size() - 1);
                Pair cur = data.get(i);
                if (cur.getStart() >= lastInResult.getEnd()) {
                    result.add(cur);
                }
            }

            StringBuilder sb = new StringBuilder();
            DecimalFormat format = new DecimalFormat("0.##");
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator('.');
            format.setDecimalFormatSymbols(decimalFormatSymbols);
            sb.append(result.size()).append("\n");
            for (int i = 0; i < result.size(); i++) {
                sb.append(format.format(result.get(i).getStart())).append(" ").append(format.format(result.get(i).getEnd())).append("\n");
            }

            writer.write(sb.toString());

        }
    }

    static class Pair implements Comparable<Pair> {
        private Double start;
        private Double end;

        public Pair(Double start, Double end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            int compareEnd = Double.compare(end, o.end);
            if (compareEnd == 0) {
                return Double.compare(start, o.start);
            }else {
                return compareEnd;
            }
        }

        public Double getStart() {
            return start;
        }

        public Double getEnd() {
            return end;
        }
    }
}
