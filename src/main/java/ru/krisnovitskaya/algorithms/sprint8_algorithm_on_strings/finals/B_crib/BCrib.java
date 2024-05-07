package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.finals.B_crib;
// https://contest.yandex.ru/contest/26133/run-report/113128648/

/*
 -- ПРИНЦИП РАБОТЫ --
Алгоритм работы сосоит из 2х этапов:
1) построение Префиксного дерева из слов
2) вычисление возможности разбиения входной строки с помощью динамического программирования.

Префиксное дерево представляет из себя Ноду, которая хранит в себе букву, мапу ребер на следующие ноды, признак терминальности.

Проверка признака разбиения входящей строки на слова представляет из себя
Создания массива dp длиной длина строки +1. Где базовый случай dp[0] = true, для строки длиной 0;
Циклом проходим по каждому сиволу строки и если dp[i] = true,
то ищем строку, если узел текущий узел терминальный, то помечаем dp[j] =true, прерываем вложенный поиск,
 если кончилась строка или следующий символ отсутсвует в ребрах.
 Результат содержится в последнем узле dp/

-- ВРЕМЕННАЯ СЛОЖНОСТЬ
N - длина строки;
M - максимальная длина слова в префиксном дереве;
K - количество слов

Добавление слов в префиксное дерево занимает O(M*K)
Поиск признака разбиения строки на слова занимает O(N*M)
Итоговая врменная сложность занимает O(M*K) + O(N*M) = O(M(K+N))

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
Префиксное дерево в худшем случае, когда все первые символы не совпадают,
занимает в памяти суммарную длину всех слов (количество слов * макс длину слова) -> O(K*M)
Для вычисления признака разбиения храним в памяи тестируемую строку и массив dp -> O(N+N) -> O(N)
Итоговая сложность O(K*M) + O(N) -> O(K*M+N)
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BCrib {

    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            String text = reader.readLine();

            Trie dictionary = fillTrie(reader);

            writer.println(dictionary.isDivide(text) ? YES : NO);
        }
    }

    private static Trie fillTrie(BufferedReader reader) throws IOException {
        int wordCount = Integer.parseInt(reader.readLine());
        Trie dictionary = new Trie();
        for (int i = 0; i < wordCount; i++) {
            dictionary.add(reader.readLine());
        }
        return dictionary;
    }

    public static class Trie {
        private final TrieNode root = new TrieNode(null);

        public void add(String word) {
            TrieNode current = root;

            for (char symbol : word.toCharArray()) {
                if(!current.getEdges().containsKey(symbol)) {
                    TrieNode newNode = new TrieNode(symbol);
                    current.getEdges().put(symbol, newNode);
                }
                current = current.getEdges().get(symbol);
            }
            current.setTerminal(true);
        }

        public boolean isDivide(String text) {
            boolean[] dp = new boolean[text.length() + 1];
            dp[0] = true;

            for (int i = 0; i < text.length(); i++) {
                if(!dp[i]) {
                    continue;
                }
                TrieNode current = root;
                for (int j = i; j < text.length() + 1; j++) {
                    if(current.isTerminal()) {
                        dp[j] = true;
                    }

                    if(j == text.length() || !current.getEdges().containsKey(text.charAt(j))) {
                        break;
                    }
                    current = current.getEdges().get(text.charAt(j));
                }
            }
            return dp[text.length()];
        }

        static class TrieNode {
            private final Character value;
            private final Map<Character, TrieNode> edges;
            private boolean terminal;

            public TrieNode(Character value) {
                this.value = value;
                this.edges = new HashMap<>();
            }

            public Character getValue() {
                return value;
            }

            public Map<Character, TrieNode> getEdges() {
                return edges;
            }

            public boolean isTerminal() {
                return terminal;
            }

            public void setTerminal(boolean terminal) {
                this.terminal = terminal;
            }
        }
    }
}
