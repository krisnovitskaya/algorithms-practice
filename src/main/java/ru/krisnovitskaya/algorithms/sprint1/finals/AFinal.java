package ru.krisnovitskaya.algorithms.sprint1.finals;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 A. Ближайший ноль
 */
public class AFinal {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int areaCount = readInt(reader);
            List<Integer> areas = readAreas(reader, areaCount);
            List<Integer> zeroIndexes = filterZeros(areas);
            String result = countMinZeroLength(areas, zeroIndexes);
            writer.write(result);
        }
    }

    private static List<Integer> filterZeros(List<Integer> areas) {
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < areas.size(); i++) {
            if(areas.get(i) == 0){
                zeros.add(i);
            }
        }
        return zeros;
    }

    private static String countMinZeroLength(List<Integer> areas, List<Integer> zeroIndexes) {
        List<Integer> result = new ArrayList<>(areas.size());

        int currentMinLength;
        int temp;
        for (int i = 0; i < areas.size(); i++) {
            currentMinLength = areas.size();
            for (int z = 0; z < zeroIndexes.size(); z++) {
                temp = Math.abs(zeroIndexes.get(z) - i);
                if (currentMinLength > temp){
                    currentMinLength = temp;
                }
                if(currentMinLength == 0){
                    break;
                }
            }
            result.add(currentMinLength);
        }
        return result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private static List<Integer> readAreas(BufferedReader reader, int areaCount) throws IOException{
        return Arrays.stream(reader.readLine().split(" ", areaCount))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
