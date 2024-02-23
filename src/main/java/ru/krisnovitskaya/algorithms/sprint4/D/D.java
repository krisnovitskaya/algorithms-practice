package ru.krisnovitskaya.algorithms.sprint4.D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class D {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            int count = Integer.parseInt(reader.readLine());

            Set<String> circles = new LinkedHashSet<>();

            String line;
            for (int i = 0; i < count; i++) {
                line = reader.readLine();
                circles.add(line);
            }

            for (String circle : circles) {
                writer.println(circle);
            }
            writer.flush();
        }
    }
}
