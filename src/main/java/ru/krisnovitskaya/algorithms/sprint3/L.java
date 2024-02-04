package ru.krisnovitskaya.algorithms.sprint3;

/*
L. Два велосипеда
 */

import java.io.*;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")){

            int dayCount = readInt(reader);
            int[] moneyHistory = readIntegerArr(dayCount, reader);//sorted
            int bikePrice = readInt(reader);

            int foundedDayIndex = findDay(moneyHistory, bikePrice, 0, dayCount, -1);
            int foundedDayIndex2 = findDay(moneyHistory, bikePrice *2, 0, dayCount, -1);

            foundedDayIndex = foundedDayIndex == -1 ? -1 : foundedDayIndex + 1;
            foundedDayIndex2 = foundedDayIndex2 == -1 ? -1 : foundedDayIndex2 + 1;

            writer.println(foundedDayIndex + " " + foundedDayIndex2);
        }
    }

    private static int findDay(int[] moneyHistory, int bikePrice, int left, int right, int defaultIndex) {
        if(left >= right){
            return defaultIndex;
        }
        int mid = (left + right) / 2;
        if(moneyHistory[mid] < bikePrice){
            return findDay(moneyHistory, bikePrice, mid + 1, right, defaultIndex);
        }else { //moneyHistory[mid] >= bikePrice
            if(defaultIndex == -1){
                defaultIndex = mid;
                return findDay(moneyHistory, bikePrice, left, mid, defaultIndex);
            }else {
                if(defaultIndex > mid){
                    defaultIndex = mid;
                    return findDay(moneyHistory, bikePrice, left, mid, defaultIndex);
                }else {
                    return findDay(moneyHistory, bikePrice, mid + 1, right, defaultIndex);
                }
            }
        }
    }

    private static int[] readIntegerArr(int dayCount, BufferedReader reader) throws IOException {
        int[] arr = new int[dayCount];
        String[] strings = reader.readLine().split(" ");
        for (int i = 0; i < dayCount; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }
        return arr;
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
