package test.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 *
 * @author Jing Zhi Bao
 */
public class StreamDemo3 {

    public static void main(String[] args) {

        String str = "My name is 007";

        // 无状态
        // map / mapToXxx
        // 把每个单词的长度打印出来
        // platMap / flatMapToXxx
        // filter
        // peek
        // unordered

        // 有状态
        // distinct
        // sorted

        // limit / skip

        Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(s -> s.length()).forEach(System.out::println);

        // flatMap A -> B 属性（是个集合），最终得到所有的A元素里面的所有B 属性集合
        // intStream 不是Stream 的子类
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> {
            System.out.println((char) i.intValue());
        });

        // peek 用于debug 是个中间操作和forEach是终止操作
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

        // limit的使用，主要用于无限流
        new Random().ints().filter(i -> i > 100 && i < 1000).limit(100).forEach(System.out::println);
    }
}
