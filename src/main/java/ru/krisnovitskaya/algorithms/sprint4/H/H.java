package ru.krisnovitskaya.algorithms.sprint4.H;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
H. Странное сравнение
 */
public class H {
    private static final String YES = "YES";
    private static final String NO = "NO";
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            String first = reader.readLine();
            String second = reader.readLine();
            String answer = process(first, second);
            writer.println(answer);
        }
    }

    private static String process(String first, String second) {
        if(first.length() != second.length()){
            return NO;
        }
        Map<Character, Character> map = new HashMap<>();
        Set<Character> setSecond = new HashSet<>();
        for (int i = 0; i < first.length(); i++) {
            Character f = first.charAt(i);
            Character s = second.charAt(i);
            Character character = map.get(f);
            if(character == null){
                if(!setSecond.contains(s)) {
                    map.put(f, s);
                    setSecond.add(s);
                }else {
                    return NO;
                }
            }else {
                if(character != s){
                    return NO;
                }
            }
        }
        return YES;
    }
}
