package ru.krisnovitskaya.algorithms.sprint3.finals.A;
// https://contest.yandex.ru/contest/23815/run-report/106568665/

/*
A. Поиск в сломанном массиве
 -- ПРИНЦИП РАБОТЫ –
По условию задачи у нас есть отсортированный массив с уникальными элементами, который может быть «сломан» - часть
последовательности хвоста может стоять в начале, но при этом в целом сортировка не нарушена.
Поэтому в основу поиска взят классический бинарный поиск с рядом дополнительных проверок. Берется средний элемент массива,
его значение сравнивается с искомым, и в зависимости от того, больше или меньше происходит дальнейшее ветвление действий:
половина проверяется на «сломанность» arr[left] <= arr[right] и принадлежность искомого элемента половине
arr[left]<= search <= arr[right] и поиск в дальнейшем происходит по классическому способу.
Если половина все еще оказывается «сломанной», то выбирается левая или правая часть и  продолжается рекурсивный поиск по сломанному методу.
-- ВРЕМЕННАЯ СЛОЖНОСТЬ
Диапазон поиска в массиве каждую итерацию уменьшается в 2 раза. Выполнение доп проверок для определение сломанности
занимают постоянное время O(1), поэтому общая временная сложность соответствует сложности бинарного поиска и равна
O(logN), где N – длина массива
 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Поиск происходит  по массиву длинной N, использование бинарного поиска в рекурсии добавляет расход памяти
для хранения стека рекурсивных вызовов - в максимуме глубина стека logN. Массив в рекурсии передается по значению ссылки
 -> следовательно дополнительной нагрузки не создает. Получается в максимуме Пространственная сложность алгоритма
 O(N+logN)

 */
public class Solution {
    public static int brokenSearch(int[] arr, int k) {
        if (arr.length == 0) {
            return -1;
        }
        return brokenSearchRecursive(arr, k, 0, arr.length -1);
    }

    private static int brokenSearchRecursive(int[] arr, int search, int indexLeft, int indexRight) {
        if (indexLeft > indexRight) {
            return -1;
        }
        int mid = indexLeft + ((indexRight - indexLeft) / 2);

        if (arr[mid] == search) {
            return mid;
        }else if (search > arr[mid]) {
            if(arr[mid] <= arr[indexRight]){
                if(search <= arr[indexRight]){
                    return classicSearchRecursive(arr, search, mid + 1, indexRight);
                } else {
                    return brokenSearchRecursive(arr, search, indexLeft, mid -1);
                }
            }else {
                return brokenSearchRecursive(arr, search, mid + 1, indexRight);
            }
        }else {//search < arr[mid]
            if(arr[indexLeft] <= arr[mid]){
                if(search >= arr[indexLeft]) {
                    return classicSearchRecursive(arr, search, indexLeft, mid - 1);
                } else {
                    return brokenSearchRecursive(arr, search, mid + 1, indexRight);
                }
            } else {
                return brokenSearchRecursive(arr, search, indexLeft, mid -1);
            }
        }
    }


    public static int classicSearchRecursive(int[] arr, int search, int indexLeft, int indexRight) {
        if (indexLeft > indexRight) {
            return -1;
        }
        int mid = indexLeft + ((indexRight - indexLeft) / 2);

        if (search == arr[mid]) {
            return mid;
        } else if (search < arr[mid]) {
            return classicSearchRecursive(
                    arr, search, indexLeft, mid - 1);
        } else {
            return classicSearchRecursive(
                    arr, search, mid + 1, indexRight);
        }
    }
}