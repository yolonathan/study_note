package test.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jing Zhi Bao
 */
public class StreamDemo4 {

    public static void main(String[] args) {

        //  Ordered 和并行流有关
        // 非短路操作
        // forEach / forEachOrdered
        // collect / toArray
        // reduce
        // min / max / count

        // 短路操作
        // findFirst / findAny
        // allMatch / anyMatch / noneMatch

        String str = "My name is 007";
        // 并行流
        str.chars().parallel().forEach( i -> System.out.println((char) i));
        // forEachOrdered 保证顺序
        str.chars().parallel().forEachOrdered( i -> System.out.println((char) i));

        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        // reduce 拼接字符串
        Optional<String> reduce = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce.orElse(""));

        // 带初始值的reduce
        String reduce1 = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce1);

        Optional<String> max = Stream.of(str.split(" ")).max(Comparator.comparingInt(String::length));
        System.out.println(max.get());


        // 短路
        // findFirst
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());


    }
}
