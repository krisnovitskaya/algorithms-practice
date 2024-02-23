package ru.krisnovitskaya.algorithms.sprint4.A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
A. Полиномиальный хеш
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            int a = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            String string = reader.readLine();
            int hash = countHash(a, m, string);
            writer.println(hash);
        }
    }

    private static int countHash(int a, int m, String string) {
        int len = string.length();
        if(len == 0){
            return 0;
        }
        if(len == 1){
            return getAsciiCode(string.charAt(0)) % m;
        }
        long result = (((long) getAsciiCode(string.charAt(0)) * a) + getAsciiCode(string.charAt(1))) % m;
        for (int i = 2; i < len; i++) {
            result = (result * a) % m + getAsciiCode(string.charAt(i)) % m;
        }
        return (int) result;
    }

    private static int getAsciiCode(char character){
        return (int) character;
    }
}
