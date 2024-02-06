package ru.krisnovitskaya.algorithms.sprint3.finals;
// https://contest.yandex.ru/contest/23815/run-report/106603677/

/* -- ПРИНЦИП РАБОТЫ --
В качестве идеи решения взят алгоритм описанный в условии задачи.
Алгоритм сортирует массив "на месте", то есть без использования дополнительной памяти. Для этого используются индексы
начала и конца подмассивов,  элементы меняются местами внутри самого массива границей служит рандомный pivot
(опорный элемент). В моем случае используется pivot = элементу по индексу из середины подмассива.
После обмена, алгоритм запускается рекурсивно по двум подмассивам.
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность алгоритма быстрой сортировки сильно зависит от выбора опрного элемента, в случае если опорный
элемент выбран неудачно -> оказался первым или последним элементом диапазона, сложность алгоритма становится
квадратичной O(N^2). Но в моей реализации опорный элемент выбирается вычислением центрального индекса подмассива, поэтому
не попадает в начало и конец (кроме последних этапов, где подмассив длиной <3). А значит временная сложность определяется
длинной прохода и глубиной рекурсивных вызовов, то есть O(N*logN)
-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
Поиск происходит по массиву длинной N, использование бинарного поиска в рекурсии добавляет расход памяти
для хранения стека рекурсивных вызовов - в максимуме глубина стека logN, так как диапазон массива для каждого вызова
рекурсивного метода уменьшается в 2 раза - схоже с бинарным поиком.  Массив в рекурсии передается по значению ссылки
 -> следовательно дополнительной нагрузки не создает. Получается в максимуме Пространственная сложность алгоритма
 O(N+logN)
 */
/*
B. Эффективная быстрая сортировка
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class B {


    public static void main(String[] args) throws IOException {
        List<Intern> interns = readInterns();
        inPlaceQuickSort(interns, 0, interns.size() - 1);
        printInterns(interns);
    }


    private static List<Intern> readInterns() throws IOException {
        List<Intern> interns = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int internCount = Integer.parseInt(reader.readLine());
            interns = new ArrayList<>(internCount);
            for (int i = 0; i < internCount; i++) {
                String[] strings = reader.readLine().split(" ");
                interns.add(new Intern(strings[0], Integer.parseInt(strings[1]), Integer.parseInt(strings[2])));
            }
        }
        return interns;
    }

    private static void printInterns(List<Intern> sorted) throws IOException {
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            for (Intern intern : sorted) {
                writer.println(intern.login);
            }
            writer.flush();
        }
    }


    public static <T extends Comparable<T>> void inPlaceQuickSort(List<T> array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int index = leftIndex + (rightIndex - leftIndex) / 2;
            T pivot = array.get(index);

            int left = leftIndex;
            int right = rightIndex;

            while (left < right) {
                while (array.get(left).compareTo(pivot) < 0) {
                    left++;
                }
                while (array.get(right).compareTo(pivot) > 0) {
                    right--;
                }
                if (left < right) {
                    swap(array, left, right);
                }
            }

            inPlaceQuickSort(array, leftIndex, left - 1);
            inPlaceQuickSort(array, right + 1, rightIndex);
        }
    }

    private static <T> void swap(List<T> array, int left, int right) {
        T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }

    static class Intern implements Comparable<Intern> {
        private String login;
        private int taskCount;
        private int penalty;

        public Intern(String login, int taskCount, int penalty) {
            this.login = login;
            this.taskCount = taskCount;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Intern o) {
            int taskCompare = Integer.compare(o.taskCount, this.taskCount);
            if (taskCompare == 0) {
                int penaltyCompare = Integer.compare(this.penalty, o.penalty);
                if (penaltyCompare == 0) {
                    return String.CASE_INSENSITIVE_ORDER.compare(this.login, o.login);
                } else {
                    return penaltyCompare;
                }
            } else {
                return taskCompare;
            }
        }
    }
}
