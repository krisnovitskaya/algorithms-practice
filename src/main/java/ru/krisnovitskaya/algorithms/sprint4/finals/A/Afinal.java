package ru.krisnovitskaya.algorithms.sprint4.finals.A;
// https://contest.yandex.ru/contest/24414/run-report/107462137/



/* -- ПРИНЦИП РАБОТЫ --
Дл хранения и поиска создана специальная структура DocumentDataBase, которая содержит в себе 2 поля:
 количество загруженых документов - используется при пересчете второго поля,
 словарь словарей data - вида Map<String, Map<Integer(1), Integer(2)>>, где
        String - уникальные слова всей системы,
        Integer(1) - индекс документа,
        Integer(2) - количество вхождений слова String в документ с индексом Integer(1),


 В первую очередь происходит последовательное считывание и наполнения словаря данными документа.
 Вторым этапом уже идет поиск-расчет релевантности документов, который для каждого запроса выполняется в
 несколько этапов:
 1) преобразование входящий строки в уникальный список слов
 2) итерация по уникальному списку слов запроса с получением информации о словарях Map<Integer(1), Integer(2)>
 3) вложенная итерация по всей длине Map<Integer(1), Integer(2)> с сохранением промежуточных рассчетных данных в
 еще один словарь relevantMap Map<Integer(3), Integer(4)>, где Integer(3) - индекс документа, Integer(4) - текущая вычисленная
 релевантность
 4) список результата relevantMap в стриме преобразуется в список пар индексов/релевантность, сортируется стандартной
  сортировкой с использованием написанного для класса пар метода компарации, обрезается до 5 и преобразуется в лист индексов,
  который добавляется к строке результата для печати.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Для создания справочника временная сложность  в худшем случае (когда все слова уникальны) будет O(N*M),
где N - количество документов, а М - количество слов в документе.

Сложность поиска по структуре для 1 запроса:
В худшем случае популярные слова встречаются в каждом документе,
Поэтому временная сложность будет примерно O(M)*O(N) + O(N)*O(NlogN)  ,где
 N - количество документов в системе
 M -количество уникальных слов в запросе
цикл по словам запроса, вложенный цикл по индексам документов, цикл по результатам, сортировка базовая( сложность quicksort)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
Пространственная сложность
Прстранственная сложность на хранение
В среднем будет O(N * M), N - количество документов, M - количество уникальных слов
Пространственная сложность на 1 поиск:
В худшем случае O(N), когда расчет индексов вернет все индексы.

Поэтому в среднем система потребляет O(N + M) + O(N) => O(N);
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/*
A. Поисковая система
*/
public class Afinal {

    public static void main(String[] args) throws IOException {
        DocumentDataBase documentDataBase;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            documentDataBase = createFilledDataBase(reader, Integer.parseInt(reader.readLine()));
            int searchCount = Integer.parseInt(reader.readLine());
            StringBuilder result = processResult(reader, searchCount, documentDataBase);
            writer.print(result);
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
        DocumentDataBase documentDataBase = new DocumentDataBase();
        for (int i = 0; i < size; i++) {
            documentDataBase.addDocument(reader.readLine());
        }
        return documentDataBase;
    }


    static class DocumentDataBase {
        private Map<String, Map<Integer, Integer>> data;
        private int countDocument;

        public DocumentDataBase() {
            this.data = new HashMap<>();
            this.countDocument = 0;
        }

        public void addDocument(String documentString) {
            countDocument++;
            StringTokenizer tokenizer = new StringTokenizer(documentString);
            String token;
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                data.merge(token, new HashMap<>(Map.of(countDocument, 1)), (indexMap, b) -> {
                    indexMap.merge(countDocument, 1, Integer::sum);
                    return indexMap;
                });
            }
        }

        public List<Integer> searchRelevantDocument(String searchString) {
            Set<String> searchSet = Arrays.stream(searchString.split(" ")).collect(Collectors.toSet());
            Map<Integer, Integer> relevantMap = new HashMap<>();
            for (String word : searchSet) {
                Map<Integer, Integer> indexesMap = data.get(word);
                if (Objects.nonNull(indexesMap)) {
                    for (Map.Entry<Integer, Integer> entry : indexesMap.entrySet()) {
                        relevantMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
                    }
                }
            }
            return relevantMap.entrySet().stream()
                    .map(entr -> new RelevantPair(entr.getKey(), entr.getValue()))
                    .sorted()
                    .limit(5)
                    .map(pair -> pair.index).collect(Collectors.toList());
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
        }
    }
}
