package ru.krisnovitskaya.algorithms.sprint1;



/*
C. Соседи
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt

Дана матрица. Нужно написать функцию, которая для элемента возвращает всех его соседей.
 Соседним считается элемент, находящийся от текущего на одну ячейку влево, вправо, вверх или вниз.
  Диагональные элементы соседними не считаются.

Например, в матрице A соседними элементами для (0, 0) будут 2 и 0. А для (2, 1) –— 1, 2, 7, 7.



Формат ввода
В первой строке задано n — количество строк матрицы. Во второй — количество столбцов m.
 Числа m и n не превосходят 1000.
  В следующих n строках задана матрица. Элементы матрицы — целые числа, по модулю не превосходящие 1000.
   В последних двух строках записаны координаты элемента, соседей которого нужно найти. Индексация начинается с нуля.

Формат вывода
Напечатайте нужные числа в возрастающем порядке через пробел.

Пример 1
Ввод
4
3
1 2 3
0 2 6
7 4 1
2 7 0
3
0
Вывод
7 7
Пример 2
Ввод	Вывод
4
3
1 2 3
0 2 6
7 4 1
2 7 0
0
0
Вывод
0 2

 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class C {

    private static List<Integer> getNeighbours(int[][] matrix, int rowId, int colId) {
        List<Integer> neighbours = new ArrayList<>(4);
        //верх
        if(rowId != 0){
            neighbours.add(matrix[rowId - 1][colId]);
        }
        //низ
        if(rowId != matrix.length - 1){
            neighbours.add(matrix[rowId + 1][colId]);
        }
        //лево
        if(colId != 0){
            neighbours.add(matrix[rowId][colId - 1]);
        }
        //право
        if(colId != matrix[rowId].length - 1){
            neighbours.add(matrix[rowId][colId + 1]);
        }
        return neighbours;
    }

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int rowsCount = readInt(reader);
            int colsCount = readInt(reader);
            int[][] matrix = readMatrix(reader, rowsCount, colsCount);
            int rowId = readInt(reader);
            int colId = readInt(reader);
            List<Integer> neighbours = getNeighbours(matrix, rowId, colId);
            writer.write(neighbours.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static void fillRow(BufferedReader reader, int[] row) throws IOException {
        String[] stringValues = reader.readLine().split(" ", row.length);
        for (int col = 0; col < row.length; col++) {
            row[col] = Integer.parseInt(stringValues[col]);
        }
    }

    private static int[][] readMatrix(BufferedReader reader, int rowsCount, int colsCount) throws IOException {
        int[][] matrix = new int[rowsCount][colsCount];
        for (int i = 0; i < rowsCount; i++) {
            fillRow(reader, matrix[i]);
        }
        return matrix;
    }
}