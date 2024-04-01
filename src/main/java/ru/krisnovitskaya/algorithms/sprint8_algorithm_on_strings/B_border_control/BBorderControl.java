package ru.krisnovitskaya.algorithms.sprint8_algorithm_on_strings.B_border_control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
B. Пограничный контроль
 */
public class BBorderControl {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            String str1 = reader.readLine();
            String str2 = reader.readLine();

            boolean result = check(str1, str2);

            printResult(writer, result);
            System.out.println(result);
        }
    }

    private static boolean check(String str1, String str2) {
        if(Math.abs(str1.length() - str2.length()) > 1) {
            return false;
        }
//        jvhkl polbtkekj
//        jvhklqpolbtkek
        int s1 = 0;
        int s2 = 0;
        int fails = 0;
        while (s1 < str1.length() && s2 < str2.length()) {
            char c1 = str1.charAt(s1);
            char c2 = str2.charAt(s2);
            if(c1 == c2){
                s1++;
                s2++;
            } else {
                if(++fails >= 2){
                    return false;
                }
                if(s1 + 1 == str1.length() || s2 + 1 == str2.length()){
                    return true;
                }
                char c1Next = str1.charAt(s1 + 1);
                char c2Next = str2.charAt(s2 + 1);
                if(c1Next == c2Next){
                    s1 = s1 + 2;
                    s2 = s2 + 2;
                }else if(c1 == c2Next){
                    s2++;
                }else if (c2 == c1Next){
                    s1++;
                }else {
                    return false;
                }
            }
        }
        int add = s1 != str1.length() || s2 != str2.length() ? 1 : 0;

        return add + fails < 2;
    }

    private static void printResult(PrintWriter writer, boolean result) {
        if(result) {
            writer.println("OK");
        } else{
            writer.println("FAIL");
        }
    }
}
