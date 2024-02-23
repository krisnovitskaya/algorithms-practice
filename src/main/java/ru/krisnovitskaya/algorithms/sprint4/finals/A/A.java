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
public class A {

    public static void main(String[] args) throws IOException {
        DocumentDataBase documentDataBase;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            documentDataBase = createFilledDataBase(reader, Integer.parseInt(reader.readLine()));
            int searchCount = Integer.parseInt(reader.readLine());
            StringBuilder result = processResult(reader, searchCount, documentDataBase);
            writer.print(result.toString());
            writer.flush();
        }
    }

    private static StringBuilder processResult(BufferedReader reader, int searchCount, DocumentDataBase documentDataBase) throws IOException {
        StringBuilder sb = new StringBuilder();
        String searchString;
        for (int i = 0; i < searchCount; i++) {
            searchString = reader.readLine();
            sb.append(documentDataBase.searchRelevantDocument(searchString).stream().map(Object::toString).collect(Collectors.joining(" ")));
            sb.append("\n");
        }
        return sb;
    }

    private static DocumentDataBase createFilledDataBase(BufferedReader reader, int size) throws IOException {
        DocumentDataBase documentDataBase = new DocumentDataBase(size);
        for (int i = 0; i < size; i++) {
            documentDataBase.addDocument(reader.readLine());
        }
        return documentDataBase;
    }


    static class DocumentDataBase {
        private List<Map<String, Integer>> data;

        public DocumentDataBase(int size) {
            this.data = new ArrayList<>(size);
        }

        public void addDocument(String documentString) {
            String[] strings = documentString.split(" ");
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < strings.length; i++) {
                map.merge(strings[i], 1, Integer::sum);
            }
            data.add(map);
        }
        
        public List<Integer> searchRelevantDocument(String searchString){
            Set<String> search = Arrays.stream(searchString.split(" ")).collect(Collectors.toSet());
            List<RelevantPair> listRelevants = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                listRelevants.add(new RelevantPair(i + 1, calcRelevant(data.get(i), search)));
            }
            return listRelevants.stream()
                    .filter(val -> val.relevantValue > 0)
                    .sorted()
                    .limit(5)
                    .map(val -> val.index)
                    .collect(Collectors.toList());
        }

        private Integer calcRelevant(Map<String, Integer> document, Set<String> search){
            return search.stream().map(searchString -> document.getOrDefault(searchString, 0))
                    .reduce(0, Integer::sum);
        }
        
    }

    static class RelevantPair implements Comparable<RelevantPair>{
        private Integer index;
        private Integer relevantValue;

        public RelevantPair(Integer index, Integer relevantValue) {
            this.index = index;
            this.relevantValue = relevantValue;
        }

        @Override
        public int compareTo(RelevantPair o) {
            return Integer.compare(o.relevantValue, this.relevantValue);
        }

        public Integer getIndex() {
            return index;
        }
    }
}
