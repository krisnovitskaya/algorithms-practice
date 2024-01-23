package ru.krisnovitskaya.algorithms.sprint1.finals;
// https://contest.yandex.ru/contest/22450/run-report/105414681/

import java.io.*;
import java.util.Arrays;

/*
 B. Ловкость рук
 */
public class BFinal2 {

    private final static int PLAYER_COUNT = 2;
    private final static int FIELD_SIZE = 4;
    private final static char EMPTY_CHAR = '.';

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            int fingerCount = readInt(reader) * PLAYER_COUNT; //2..10;
            int[] fieldCounted = readAndPrepareField(reader);

            int result = countGameResult(fingerCount, fieldCounted);
            writeResult(writer, result);
        }
    }

    private static int countGameResult(int fingerCount, int[] fieldCounted) {
        int result = 0;
        for (int i = 1; i < fieldCounted.length; i++) {
            if (fieldCounted[i] <= fingerCount && fieldCounted[i] != 0) {
                result++;
            }
        }
        return result;
    }

    //arr of count (0)1..9
    private static int[] readAndPrepareField(BufferedReader reader) throws IOException {
        int[] arr = new int[10];
        Arrays.fill(arr, 0);
        for (int i = 0; i < FIELD_SIZE; i++) {
            reader.readLine().chars().mapToObj(item -> (char) item)
                    .filter(character -> EMPTY_CHAR != character)
                    .map(Character::getNumericValue)
                    .forEach(integer -> arr[integer]++);
        }
        return arr;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static void writeResult(BufferedWriter writer, int result) throws IOException {
        writer.write(String.valueOf(result));
    }
}
