package ru.krisnovitskaya.algorithms.sprint4.finals.A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/*
A. Поисковая система
*/
public class A2 {

    public static void main(String[] args) throws IOException {
        DocumentDataBase documentDataBase;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
//            while (reader.ready()){
//                String res = reader.readLine();
//                if(!res.isEmpty()) {
//                    writer.println(res);
//                }
//            }
            documentDataBase = createFilledDataBase(reader, Integer.parseInt(reader.readLine()));
            int searchCount = Integer.parseInt(reader.readLine());
            long start_reading = System.currentTimeMillis();
            StringBuilder result = processResult(reader, searchCount, documentDataBase);
            long end_reading = System.currentTimeMillis();
            System.out.println(end_reading - start_reading);
            writer.print(result.toString());
            writer.flush();
        }
    }

    private static StringBuilder processResult(BufferedReader reader, int searchCount, DocumentDataBase documentDataBase) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<Integer> integers;
        for (int i = 0; i < searchCount; i++) {
            integers = documentDataBase.searchRelevantDocument(reader.readLine());
            for (int j = 0; j < integers.size(); j++) {
                sb.append(integers.get(j)).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }

    private static DocumentDataBase createFilledDataBase(BufferedReader reader, int size) throws IOException {
        long start_reading = System.currentTimeMillis();
        DocumentDataBase documentDataBase = new DocumentDataBase(size);
        for (int i = 0; i < size; i++) {
            documentDataBase.addDocument(reader.readLine());
        }
        long end_reading = System.currentTimeMillis();
        System.out.println(end_reading - start_reading);
        return documentDataBase;
    }


    static class DocumentDataBase {
        private List<Map<String, Integer>> data;
        private Map<String, Set<Integer>> contentIndexes;

        public DocumentDataBase(int size) {
            this.data = new ArrayList<>(size);
            this.contentIndexes = new HashMap<>();
        }

        public void addDocument(String documentString) {
            StringTokenizer tokenizer = new StringTokenizer(documentString);
            Map<String, Integer> map = new HashMap<>();
            String token;
            int nextIndex = data.size();
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                map.merge(token, 1, Integer::sum);
                contentIndexes.merge(token, new HashSet<>(Set.of(nextIndex)), (set, s) -> {
                    set.add(nextIndex);
                    return set;
                });
            }
            data.add(map);
        }

        public List<Integer> searchRelevantDocument(String searchString) {
            Set<String> search = Arrays.stream(searchString.split(" ")).collect(Collectors.toSet());

            Set<Integer> matchingDocIndex = matchDocument(search);

            Integer relevant;
            TreeSet<RelevantPair> set = new TreeSet<>();
            for (Integer docIndex : matchingDocIndex) {
                relevant = calcRelevant(data.get(docIndex), search);
                if (relevant > 0) {
                    if (set.size() < 5) {
                        set.add(new RelevantPair(docIndex + 1, relevant));
                    } else {
                        if (set.last().relevantValue < relevant) {
                            set.remove(set.last());
                            set.add(new RelevantPair(docIndex + 1, relevant));
                        }
                    }
                }
            }
            return set.stream().map(val -> val.index).collect(Collectors.toList());
        }

        private Set<Integer> matchDocument(Set<String> search) {
            Set<Integer> matching = new HashSet<>();
            for (String word : search) {
                if(contentIndexes.containsKey(word)){
                    matching.addAll(contentIndexes.get(word));
                }
            }
            return matching;
        }

        private Integer calcRelevant(Map<String, Integer> document, Set<String> search) {
            Integer relevant = 0;
            Integer res;
            for (String str : search) {
                res = document.get(str);
                if (res != null) {
                    relevant = relevant + res;
                }
            }
            return relevant;
        }
    }

    static class RelevantPair implements Comparable<RelevantPair> {
        private Integer index;
        private Integer relevantValue;

        public RelevantPair(Integer index, Integer relevantValue) {
            this.index = index;
            this.relevantValue = relevantValue;
        }

        @Override
        public int compareTo(RelevantPair o) {
            int compare = Integer.compare(o.relevantValue, this.relevantValue);
            if (compare == 0) {
                return Integer.compare(this.index, o.index);
            } else {
                return compare;
            }
//            return Integer.compare(o.relevantValue, this.relevantValue);
        }

        public Integer getIndex() {
            return index;
        }

    }
}
