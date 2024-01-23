package ru.krisnovitskaya.algorithms.sprint1;

/*
D. Хаотичность погоды
Язык	Ограничение времени	Ограничение памяти	Ввод	Вывод
Все языки	0.2 секунды	64Mb	стандартный ввод или input.txt	стандартный вывод или output.txt
OpenJDK Java 11	0.5 секунд	64Mb
C# (MS .NET 6.0 + ASP)	0.5 секунд	64Mb
Kotlin 1.8.0 (JRE 11)	0.6 секунд	64Mb
C# (MS .NET 5.0 + ASP)	0.5 секунд	64Mb
Метеорологическая служба вашего города решила исследовать погоду новым способом.

Под температурой воздуха в конкретный день будем понимать максимальную температуру в этот день.
Под хаотичностью погоды за n дней служба понимает количество дней, в которые температура строго больше, чем в день до (если такой существует) и в день после текущего (если такой существует). Например, если за 5 дней максимальная температура воздуха составляла [1, 2, 5, 4, 8] градусов, то хаотичность за этот период равна 2: в 3-й и 5-й дни выполнялись описанные условия.
Определите по ежедневным показаниям температуры хаотичность погоды за этот период.

Заметим, что если число показаний n=1, то единственный день будет хаотичным.

Формат ввода
В первой строке дано число n –— длина периода измерений в днях, 1 ≤ n≤ 105. Во второй строке даны n целых чисел –— значения температуры в каждый из n дней. Значения температуры не превосходят 273 по модулю.

Формат вывода
Выведите единственное число — хаотичность за данный период.

Пример 1
Ввод	                    Вывод
7                               3
-1 -10 -8 0 2 0 5

Пример 2
Ввод	                    Вывод
5                               2
1 2 5 4 8

 */


import java.io.*;

public class D {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            int arrLength = readInt(reader);
            int result = calcResult(readTemps(reader, arrLength));
            writer.write(String.valueOf(result));
        }
    }

    private static int calcResult(int[] temperatures) {
        int result = 0;

        if(temperatures.length == 1) {
            result = 1;
            return result;
        }

        for (int i = 1; i < temperatures.length - 1; i++) {
            if(temperatures[i] > temperatures[i - 1] && temperatures[i] > temperatures[i + 1]){
                result++;
            }
        }
        //check first
        if(temperatures[0] > temperatures[1]){
            result++;
        }
        //check last
        if(temperatures[temperatures.length - 1] > temperatures[temperatures.length - 2]){
            result++;
        }
        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readTemps(BufferedReader reader, int size) throws IOException {
        String[] strings = reader.readLine().split(" ", size);
        int[] temps = new int[size];
        for (int i = 0; i < size; i++) {
            temps[i] = Integer.parseInt(strings[i]);
        }
        return temps;
    }
}
