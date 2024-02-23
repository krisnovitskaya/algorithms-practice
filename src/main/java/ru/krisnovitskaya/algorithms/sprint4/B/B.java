package ru.krisnovitskaya.algorithms.sprint4.B;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
B. Сломай меня
 */
public class B {
    public static void main(String[] args) {
        int a = 1000;
        int m = 123_987_123;

        String alphabet = "abcdefghijklmnopqrstuvwxwz";
        Map<Integer, String> map = new HashMap<>();

        Random random = new Random();
        while (true){
            String generated = "";
            for (int i = 0; i < alphabet.length(); i++) {
                generated = generated.concat(String.valueOf(alphabet.charAt(random.nextInt(alphabet.length()))));
            }
            int hash = countHash(a, m, generated);
            if(map.containsKey(hash)){
                System.out.println(map.get(hash));
                System.out.println(generated);
                break;
            }else {
                map.put(hash, generated);
            }
        }
        System.out.println(map);
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
