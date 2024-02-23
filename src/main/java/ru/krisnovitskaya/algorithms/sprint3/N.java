package ru.krisnovitskaya.algorithms.sprint3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
N. Клумбы
 */
public class N {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")
        ) {
            List<List<Integer>> data = readData(reader);
            data.sort((first, second) -> first.get(0) == second.get(0) ? first.get(1) - second.get(1) : first.get(0) - second.get(0));
            List<List<Integer>> result = process(data);
            writer.println(prepareResultString(result));
        }
    }

    private static StringBuilder prepareResultString(List<List<Integer>> result) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> list : result) {
            sb.append(list.get(0)).append(" ").append(list.get(1)).append("\n");
        }
        return sb;
    }

    private static List<List<Integer>> process(List<List<Integer>> data) {
        List<List<Integer>> result = new ArrayList<>();

        Integer start = data.get(0).get(0);
        Integer end = data.get(0).get(1);
        for (int i = 0; i < data.size(); i++) {
            if(start <= data.get(i).get(0) && data.get(i).get(0)<= end){
                if(data.get(i).get(1) > end){
                    end = data.get(i).get(1);
                }
            }else {
                result.add(List.of(start, end));
                start = data.get(i).get(0);
                end = data.get(i).get(1);
            }
        }
        result.add(List.of(start, end));
        return result;
    }

    private static List<List<Integer>> readData(BufferedReader reader) throws IOException{
        Integer count = Integer.parseInt(reader.readLine());
        List<List<Integer>> data = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String[] s = reader.readLine().split(" ");
            data.add(List.of(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }
        return data;
    }
}
/*
N. Клумбы
Язык	Ограничение времени	Ограничение памяти	Ввод	Вывод
Все языки	1 секунда	64Mb	стандартный ввод или input.txt	стандартный вывод или output.txt
OpenJDK Java 11	2 секунды	128Mb
C# (MS .NET 6.0 + ASP)	1 секунда	128Mb
Kotlin 1.8.0 (JRE 11)	2 секунды	128Mb
C# (MS .NET 5.0 + ASP)	1 секунда	128Mb
Алла захотела, чтобы у неё под окном были узкие клумбы с тюльпанам. На схеме земельного участка клумбы обозначаются просто горизонтальными отрезками, лежащими на одной прямой. Для ландшафтных работ было нанято n садовников. Каждый из них обрабатывал какой-то отрезок на схеме. Процесс был организован не очень хорошо, иногда один и тот же отрезок или его часть могли быть обработаны сразу несколькими садовниками. Таким образом, отрезки, обрабатываемые двумя разными садовниками, сливаются в один. Непрерывный обработанный отрезок затем станет клумбой. Нужно определить границы будущих клумб.
Рассмотрим примеры.
Пример 1:
Даны 4 отрезка:
[
7
,
8
]
,
[
7
,
8
]
 ,
[
2
,
3
]
,
[
6
,
1
0
]
. Два одинаковых отрезка
[
7
,
8
]
 и
[
7
,
8
]
 сливаются в один, но потом их накрывает отрезок
[
6
,
1
0
]
. Таким образом, имеем две клумбы с координатами
[
2
,
3
]
 и
[
6
,
1
0
]
.
Пример 2
Во втором сэмпле опять 4 отрезка:
[
2
,
3
]
,
[
3
,
4
]
,
[
3
,
4
]
,
[
5
,
6
]
. Отрезки
[
2
,
3
]
,
[
3
,
4
]
 и
[
3
,
4
]
 сольются в один отрезок
[
2
,
4
]
. Отрезок
[
5
,
6
]
 ни с кем не объединяется, добавляем его в ответ.

Формат ввода
В первой строке задано количество садовников
n
. Число садовников не превосходит
1
0
0
0
0
0
.
В следующих
n
 строках через пробел записаны координаты клумб в формате: start end, где start —– координата начала, end —– координата конца. Оба числа целые, неотрицательные и не превосходят
1
0
7
. start строго меньше, чем end.

Формат вывода
Нужно вывести координаты каждой из получившихся клумб в отдельных строках. Данные должны выводиться в отсортированном порядке —– сначала клумбы с меньшими координатами, затем —– с бОльшими.
Пример 1
Ввод	Вывод
4
7 8
7 8
2 3
6 10
Вывод
2 3
6 10
Пример 2
Ввод	Вывод
4
2 3
5 6
3 4
3 4
Вывод
2 4
5 6
Пример 3
Ввод	Вывод
6
1 3
3 5
4 6
5 6
2 4
7 10
Вывод
1 6
7 10
 */
