package ru.krisnovitskaya.algorithms.sprint1.finals;
// https://contest.yandex.ru/contest/22450/run-report/105362187/
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*
 B. Ловкость рук
 */
public class BFinal {

    private final static int PLAYER_COUNT = 2;
    private final static int FIELD_SIZE = 4;
    private final static char EMPTY_CHAR = '.';

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int fingerCount = readInt(reader) * PLAYER_COUNT; //2..10;
            Map<Integer, Integer> fieldCounted = readAndPrepareField(reader);
            String result = countGameResult(fingerCount, fieldCounted);
            writer.write(result);
        }
    }

    private static String countGameResult(int fingerCount, Map<Integer, Integer> fieldCounted) {
        int result = 0;
        for (Integer key : fieldCounted.keySet()) {
            if(fieldCounted.get(key) <= fingerCount){
                result++;
            }
        }
        return String.valueOf(result);
    }

    //map of 1..9 and count
    private static Map<Integer, Integer> readAndPrepareField(BufferedReader reader) throws IOException{
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < FIELD_SIZE; i++) {
            reader.readLine().chars().mapToObj(item -> (char) item)
                    .filter(character -> EMPTY_CHAR != character)
                    .map(Character::getNumericValue)
                    .forEach(integer -> map.merge(integer, 1, Integer::sum));
        }
        return map;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
