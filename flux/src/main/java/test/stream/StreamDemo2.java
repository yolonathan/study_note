package test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Jing Zhi Bao
 */
public class StreamDemo2 {

    public static void main(String[] args) {
        // 集合
        List<String> list = new ArrayList();
        list.add("My Name");
        Stream<String> stream = list.stream();
        Stream<String> stringStream = list.parallelStream();

        // 数组
        int[] nums = { 1, 4, 6, 7, 45, 34 };
        Arrays.stream(nums);


        // 创建数字流
        IntStream.of(1, 2, 3);
        IntStream.rangeClosed(1, 10);

        // 使用random 创建一个无限流
        IntStream ints = new Random().ints().limit(10);

        // 自己产生流
        Random random = new Random();
        Stream.generate(() -> random.nextInt()).limit(10);


    }
}
